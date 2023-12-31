package com.lorempicsum.photos.data.datastore

import com.lorempicsum.photos.data.source.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockUserPreferences : UserPreferences {

    override fun getStoredData(prefKey: String): Flow<String> {
        return flow {
            while (true) {
                emit("Mock Author") // Emits the result of the request to the flow
            }
        }
    }

    override suspend fun storeData(prefKey: String, data: String) {
        // do nothing
    }
}