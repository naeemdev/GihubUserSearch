package com.naeemdev.githubuser.domain

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val errorType: ErrorType, val statusCode: Int? = null) : Resource<T>()
}
