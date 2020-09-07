package com.javadsh98.usersearcher.data.repository

import com.javadsh98.hilttest.data.remote.model.UserDetailResponse
import com.javadsh98.hilttest.data.remote.model.UserItemResponse
import com.javadsh98.hilttest.data.remote.model.UserRepoItemResponse
import com.javadsh98.hilttest.data.remote.model.UserSearchResultResponse
import com.javadsh98.usersearcher.data.remote.api.SearchApi
import com.javadsh98.usersearcher.data.remote.api.UserApi
import com.javadsh98.usersearcher.data.remote.ext.toFlow
import com.javadsh98.usersearcher.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import com.javadsh98.usersearcher.data.remote.ext.Result
import timber.log.Timber
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val searchApi: SearchApi, val userApi: UserApi)
    : IRepository{

    override suspend fun getUser(username: String): Flow<Result<UserSearchResultResponse>> {
        val res = searchApi.getUser(username).toFlow()
        Timber.tag("searchviewmodel").v("repository")
        return res
    }

    override suspend fun getRepos(username: String): Flow<Result<List<UserRepoItemResponse>>> {
        return userApi.getRepos(username).toFlow()
    }

    override suspend fun getFollower(username: String): Flow<Result<List<UserItemResponse>>> {
        return userApi.getFollower(username).toFlow()
    }

    override suspend fun getFollowing(username: String): Flow<Result<List<UserItemResponse>>> {
        return userApi.getFollowing(username).toFlow()
    }

    override suspend fun getUserDetail(username: String): Flow<Result<UserDetailResponse>> {
        return userApi.getDetail(username).toFlow()
    }
}