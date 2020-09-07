package com.javadsh98.usersearcher.presentation.fragment.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.javadsh98.hilttest.data.remote.model.UserDetailResponse
import com.javadsh98.hilttest.data.remote.model.UserItemResponse
import com.javadsh98.usersearcher.domain.usecase.DetailDomain
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
     var detailDomain: DetailDomain
    , @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _userDetail = MutableLiveData<UserDetailResponse>()
    val userDetail: LiveData<UserDetailResponse>
        get() = _userDetail

    fun getUser(username: String){
        viewModelScope.launch {
            detailDomain.execute(username, success = {
                _userDetail.value = it
            })
        }
    }

}