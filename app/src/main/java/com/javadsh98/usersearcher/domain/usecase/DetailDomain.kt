package com.javadsh98.usersearcher.domain.usecase

import com.javadsh98.hilttest.data.remote.model.UserDetailResponse
import com.javadsh98.usersearcher.data.remote.ext.Result
import com.javadsh98.usersearcher.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailDomain @Inject constructor(val iRepository: IRepository): BaseDomain<String, UserDetailResponse>() {

    override suspend fun build(parameter: String): Flow<Result<UserDetailResponse>> {
        return iRepository.getUserDetail(parameter)
    }


}