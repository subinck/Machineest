package com.example.machineest.data.network

import com.example.machineest.data.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetUserDetailsApi {
    @GET("users/{id}")
     fun getUserDetails(
        @Path("id")id:Int
    ): Call<User>
}