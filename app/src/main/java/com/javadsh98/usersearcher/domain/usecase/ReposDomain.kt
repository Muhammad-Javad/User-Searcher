package com.javadsh98.usersearcher.domain.usecase

import com.javadsh98.hilttest.presentation.model.RepoItemModel
import com.javadsh98.usersearcher.data.remote.ext.Result
import com.javadsh98.usersearcher.data.remote.ext.map
import com.javadsh98.usersearcher.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class ReposDomain @Inject constructor(val iRepository: IRepository): BaseDomain<String, List<RepoItemModel>>() {

    override suspend fun build(parameter: String): Flow<Result<List<RepoItemModel>>> {
        return iRepository.getRepos(parameter).map {
            it.map {
                it.map {
                    RepoItemModel(
                        it.id,
                        it.htmlUrl,
                        it.name,
                        it.description.orEmpty(),
                        it.language.orEmpty(),
                        it.stargazersCount,
                        it.forksCount,
                        it.fork,
                        it.owner.login,
                        it.owner.avatarUrl,
                        LocalDateTime.ofInstant(
                            Instant.parse(it.createdAt),
                            ZoneId.systemDefault()
                        ),
                        LocalDateTime.ofInstant(
                            Instant.parse(it.updatedAt),
                            ZoneId.systemDefault()
                        )
                    )
                }
            }
        }
    }


}