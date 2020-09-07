package com.javadsh98.usersearcher.domain.usecase

import com.javadsh98.hilttest.presentation.model.UserItemModel
import com.javadsh98.usersearcher.data.remote.ext.Result
import com.javadsh98.usersearcher.data.remote.ext.map
import com.javadsh98.usersearcher.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserSearchDomain @Inject constructor(val iRepository: IRepository): BaseDomain<String, List<UserItemModel>>() {

    override suspend fun build(parameter: String): Flow<Result<List<UserItemModel>>> {
        val res =  iRepository.getUser(parameter).map {
            it.map {
                it.items.map {
                    UserItemModel(
                        it.id, it.login, it.avatarUrl
                    )
                }
            }
        }
        Timber.tag("searchviewmodel").v("domain")
        return res
    }


}