package com.transactcampus.assessment.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserPreferences(private val dataStore: DataStore<Preferences>) : UserPreferences {
    companion object {
        const val SELECTED_AUTHOR_KEY = "selected_author"
    }

    override fun getStoredData(prefKey: String): Flow<String> {
        return dataStore.data
            .map { preferences ->
                preferences[stringPreferencesKey(prefKey)] ?: ""
            }
    }

    override suspend fun storeData(prefKey: String, data: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(prefKey)] = data
        }
    }

}