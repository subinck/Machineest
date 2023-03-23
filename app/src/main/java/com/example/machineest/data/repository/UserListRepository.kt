package com.example.machineest.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.machineest.data.models.User
import com.example.machineest.data.network.GetUserDetailsApi
import com.example.machineest.data.network.GetUserListApi
import com.example.machineest.ui.paging.PAGE_SIZE
import com.example.machineest.ui.paging.UserListPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserListRepository @Inject constructor( private val userApi: GetUserListApi,private val detailsApi: GetUserDetailsApi) {

     fun getUserList(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UserListPagingSource(service = userApi)
            }
        ).flow
    }

    suspend fun makeApiCall(id:Int, liveData: MutableLiveData<User>){
        val call: Call<User> =detailsApi.getUserDetails(id =id )
        call.enqueue(object: Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                liveData.postValue(response.body())
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                liveData.postValue(null)
            }
        })


    }
}