package com.example.machineest.data.models


import com.example.machineest.data.models.Address
import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("department")
    val department: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("title")
    val title: String?
)