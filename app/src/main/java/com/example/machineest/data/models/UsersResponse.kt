package com.example.machineest.data.models


import com.example.machineest.data.models.User
import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("users")
    val users: List<User>?
)