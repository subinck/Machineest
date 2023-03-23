package com.example.machineest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machineest.data.models.User
import com.example.machineest.data.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsFragmentViewModel  @Inject constructor(
    private  val repository: UserListRepository
): ViewModel(){
    private var liveDataList: MutableLiveData<User> = MutableLiveData()

    fun getLiveDataObserver():MutableLiveData<User>{
        return liveDataList
    }
    fun loadUserDetails(id:Int){
        viewModelScope.launch {
            repository.makeApiCall(id,liveDataList)
        }
    }
}