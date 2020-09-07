package com.javadsh98.usersearcher.presentation.fragment.search

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.javadsh98.hilttest.presentation.model.UserItemModel
import com.javadsh98.usersearcher.data.remote.ext.Result
import com.javadsh98.usersearcher.domain.usecase.UserSearchDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
class SearchViewModel @ViewModelInject constructor(
    var userSearchDomain: UserSearchDomain
    , @Assisted private val savedStateHandle: SavedStateHandle): ViewModel(){

    private val _userSearched = MutableLiveData<List<UserItemModel>>()
    val userSearched : LiveData<List<UserItemModel>>
        get() = _userSearched

    private val _message = MutableLiveData<String>()
    val message : LiveData<String>
        get() = _message

    fun searchUser(username: StateFlow<String>){
        Timber.tag("searchviewmodel").v("onSearchUser")
        viewModelScope.launch {
            username
                .debounce(350)
                .filter {
                    !it.isNullOrBlank()
                }
                .flatMapLatest {
                    userSearchDomain.build(it)
                }
                .collect {
                    Timber.tag("searchviewmodel").v("on result")
                    when(it){
                        is Result.Error -> {
                            Timber.i(it.message)
                        }
                        is Result.Success -> {
                            _userSearched.value = it.data
                        }
                    }
                }

        }
    }

}
