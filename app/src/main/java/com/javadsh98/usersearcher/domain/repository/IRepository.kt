package com.javadsh98.usersearcher.domain.repository

import com.javadsh98.hilttest.data.remote.model.UserDetailResponse
import com.javadsh98.hilttest.data.remote.model.UserItemResponse
import com.javadsh98.hilttest.data.remote.model.UserRepoItemResponse
import com.javadsh98.hilttest.data.remote.model.UserSearchResultResponse
import kotlinx.coroutines.flow.Flow
import com.javadsh98.usersearcher.data.remote.ext.Result

interface IRepository {

    suspend fun getUser(username: String): Flow<Result<UserSearchResultResponse>>
    suspend fun getRepos(username: String): Flow<Result<List<UserRepoItemResponse>>>
    suspend fun getFollower(username: String): Flow<Result<List<UserItemResponse>>>
    suspend fun getFollowing(username: String): Flow<Result<List<UserItemResponse>>>
    suspend fun getUserDetail(username: String): Flow<Result<UserDetailResponse>>
}