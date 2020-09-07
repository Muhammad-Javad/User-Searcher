package com.javadsh98.usersearcher.data.remote.ext


sealed class Result<out T> {

    data class Success<T>(val data: T): Result<T>()
    data class Error(val message: String): Result<Nothing>()
}

fun <T, R> Result<T>.map(mapper: (T) -> R): Result<R> {
    return when(this){
        is Result.Success -> {
            Result.Success(mapper(this.data))
        }
        is Result.Error -> {
            Result.Error(this.message)
        }
    }
}