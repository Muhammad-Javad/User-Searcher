package com.javadsh98.usersearcher.domain.usecase

import com.javadsh98.usersearcher.data.remote.ext.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

abstract class BaseDomain<Parameter, T>() {

    abstract suspend fun build(parameter: Parameter): Flow<Result<T>>

    suspend fun execute(parameter: Parameter): Flow<Result<T>>{
        return build(parameter)
    }

    suspend fun execute(parameter: Parameter, success: (T) -> Unit, error: (String) -> Unit = {}){
        build(parameter).collect {
            when(it){
                is Result.Success -> {
                    success(it.data)
                }
                is Result.Error -> {
                    error(it.message)
                }
            }
        }
    }

}