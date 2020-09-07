package com.javadsh98.usersearcher.data.remote.api

import com.javadsh98.hilttest.data.remote.model.UserDetailResponse
import com.javadsh98.hilttest.data.remote.model.UserItemResponse
import com.javadsh98.hilttest.data.remote.model.UserRepoItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("/users/{username}/followers")
    suspend fun getFollower(@Path("username") username: String): Response<List<UserItemResponse>>

    @GET("/users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): Response<List<UserItemResponse>>

    @GET("/users/{username}")
    suspend fun getDetail(@Path("username") username: String): Response<UserDetailResponse>

    @GET("/users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String): Response<List<UserRepoItemResponse>>

}