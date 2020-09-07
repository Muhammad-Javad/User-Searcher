package com.javadsh98.usersearcher.domain.usecase

import com.javadsh98.hilttest.presentation.model.UserItemModel
import com.javadsh98.usersearcher.data.remote.ext.Result
import com.javadsh98.usersearcher.data.remote.ext.map
import com.javadsh98.usersearcher.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FollowingDomain @Inject constructor(val iRepository: IRepository): BaseDomain<String, List<UserItemModel>>() {

    override suspend fun build(parameter: String): Flow<Result<List<UserItemModel>>> {
        return iRepository.getFollowing(parameter).map {
            it.map {
                it.map {
                    UserItemModel(
                        it.id
                        , it.login
                        , it.avatarUrl
                    )
                }
            }
        }
    }


}