package com.example.machineest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.machineest.data.models.User
import com.example.machineest.data.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel  @Inject constructor(
    private  val repository: UserListRepository
): ViewModel(){

    fun loadUsersByPage(): Flow<PagingData<User>> {
        return repository.getUserList()
            .cachedIn(viewModelScope)
    }
}