package com.lorempicsum.photos.data.source.remote.mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.lorempicsum.photos.data.api.ImageApiService
import com.lorempicsum.photos.data.source.database.ImageDatabase
import com.lorempicsum.photos.data.source.database.entity.AuthorEntity
import com.lorempicsum.photos.data.source.database.entity.ImageEntity
import com.lorempicsum.photos.data.source.database.entity.RemoteKeysEntity
import retrofit2.HttpException
import java.io.IOException

// Lorem Picsum page API is 1 based: https://developer.github.com/v3/#pagination
private const val PICSUM_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class ImageRemoteMediator(
    private val query: String?,
    private val imageDatabase: ImageDatabase,
    private val networkService: ImageApiService
) : RemoteMediator<Int, ImageEntity>() {

    override suspend fun initialize(): InitializeAction {
        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
        // append until refresh has succeeded. In cases where we don't mind showing out-of-date,
        // cached offline data, we can return SKIP_INITIAL_REFRESH instead to prevent paging
        // triggering remote refresh.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageEntity>
    ): MediatorResult {

        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: PICSUM_STARTING_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            // If fetching images by author then don't worry about pagination
            if (query != null) {
                return MediatorResult.Success(
                    endOfPaginationReached = true
                )
            }

            val images = networkService.getImagesList(
                page = currentPage,
                limit = state.config.pageSize
            )
            Log.d("ImageRemoteMediator", "images.size ${images.size}")
            val endOfPaginationReached = images.isEmpty()

            imageDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    imageDatabase.remoteKeysDao().clearRemoteKeys()
                    imageDatabase.imageDao().clearAll()
                }
                val prevKey =
                    if (currentPage == PICSUM_STARTING_PAGE_INDEX) null else currentPage - 1
                val nextKey = if (endOfPaginationReached) null else currentPage + 1
                val keys = images.map {
                    RemoteKeysEntity(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }

                imageDatabase.remoteKeysDao().insertAll(keys)
                imageDatabase.imageDao().upsertAll(images)

                val currentSelectedAuthor = imageDatabase.authorDao().getCurrentSelectedAuthor()
                val authorList = currentSelectedAuthor?.let {
                    images.map {
                        AuthorEntity(
                            authorName = it.author,
                            isSelected = currentSelectedAuthor.authorName == it.author
                        )
                    }.distinct()
                } ?: images.map { AuthorEntity(authorName = it.author) }.distinct()

                imageDatabase.authorDao().upsertAuthors(authorList)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ImageEntity>): RemoteKeysEntity? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                // Get the remote keys of the last item retrieved
                imageDatabase.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ImageEntity>): RemoteKeysEntity? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                // Get the remote keys of the first items retrieved
                imageDatabase.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ImageEntity>
    ): RemoteKeysEntity? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                imageDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
    }
}