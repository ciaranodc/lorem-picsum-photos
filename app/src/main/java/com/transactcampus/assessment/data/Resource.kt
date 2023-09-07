package com.transactcampus.assessment.data

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    fun isSuccess(): Boolean {
        return this is Success<*>
    }
    fun isError(): Boolean {
        return this is Error
    }
    fun isLoading(): Boolean {
        return this is Loading
    }
}