package com.transactcampus.assessment.data.datastore

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthorPrefs(
    private val sharedPrefs: SharedPreferences,
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val SELECTED_AUTHOR = stringPreferencesKey("selected_author")
    }

    val lastSelectedAuthor: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[SELECTED_AUTHOR] ?: ""
        }

    suspend fun saveSelectedAuthor(author: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_AUTHOR] = author
        }
    }

}