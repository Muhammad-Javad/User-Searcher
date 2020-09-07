package com.javadsh98.usersearcher.presentation.fragment.repo

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.javadsh98.hilttest.presentation.model.RepoItemModel
import com.javadsh98.usersearcher.domain.usecase.FollowingDomain
import com.javadsh98.usersearcher.domain.usecase.ReposDomain
import kotlinx.coroutines.launch

class ReposViewModel @ViewModelInject public constructor(
    var reposDomain: ReposDomain,
    @Assisted val savedStateHandle: SavedStateHandle
): ViewModel(){


    private val _repos = MutableLiveData<List<RepoItemModel>>()
    val repos: LiveData<List<RepoItemModel>>
        get() = _repos

    fun getRepos(username: String){
        viewModelScope.launch {
            reposDomain.execute(username, success = {
                _repos.value = it
            })
        }
    }

}