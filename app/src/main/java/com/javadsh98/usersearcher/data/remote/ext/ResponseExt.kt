package com.javadsh98.usersearcher.data.remote.ext

import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber

fun <T> Response<T>.toFlow() = flow<Result<T>> {
    val network = this@toFlow
    if(network.isSuccessful && network.body() != null){

        Timber.tag("toflow").v("${network.body()!!}")
        emit(Result.Success(network.body()!!))
    }else{
        emit(Result.Error(network.message()))
    }

}