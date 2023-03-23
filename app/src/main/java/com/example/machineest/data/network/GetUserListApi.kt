package com.example.machineest.data.network

import com.example.machineest.data.models.User
import com.example.machineest.data.models.UsersResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetUserListApi {
    @GET("users")
    suspend fun getUserList(
        @Query("limit")limit:Int
    ): Response<UsersResponse>

}