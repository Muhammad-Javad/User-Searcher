package com.javadsh98.usersearcher.data.remote.api

import com.javadsh98.hilttest.data.remote.model.UserDetailResponse
import com.javadsh98.hilttest.data.remote.model.UserItemResponse
import com.javadsh98.hilttest.data.remote.model.UserRepoItemResponse
import com.javadsh98.hilttest.data.remote.model.UserSearchResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {

    @GET("search/users")
    suspend fun getUser(@Query("q") query: String): Response<UserSearchResultResponse>

}