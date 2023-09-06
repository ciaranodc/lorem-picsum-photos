package com.transactcampus.assessment.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        val SELECTED_AUTHOR = stringPreferencesKey("selected_author")
    }

    val storedAuthor: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[SELECTED_AUTHOR] ?: ""
        }

    suspend fun storeSelectedAuthor(author: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_AUTHOR] = author
        }
    }

}