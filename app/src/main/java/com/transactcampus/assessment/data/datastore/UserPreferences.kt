package com.transactcampus.assessment.data.datastore

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    fun getStoredData(prefKey: String): Flow<String>

    suspend fun storeData(prefKey: String, data: String)
}