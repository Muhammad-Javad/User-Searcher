package com.javadsh98.usersearcher.presentation.fragment.social

import android.widget.Switch
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.javadsh98.hilttest.presentation.model.UserItemModel
import com.javadsh98.usersearcher.domain.usecase.FollowerDomain
import com.javadsh98.usersearcher.domain.usecase.FollowingDomain
import kotlinx.coroutines.launch

class SocialViewModel @ViewModelInject constructor(
    val followerDomain: FollowerDomain,
    val followingDomain: FollowingDomain,
    @Assisted private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _socialUser = MutableLiveData<List<UserItemModel>>()
    val socialUser: LiveData<List<UserItemModel>>
        get() = _socialUser

    fun getSocial(type: Int, username: String){
        viewModelScope.launch {
            if (type == 0){
                followingDomain.execute(username, success = {
                    _socialUser.value = it
                })
            }else if(type == 1){
                followerDomain.execute(username, success = {
                    _socialUser.value = it
                })
            }
        }
    }

}