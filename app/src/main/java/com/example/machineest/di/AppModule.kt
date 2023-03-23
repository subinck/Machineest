package com.example.machineest.di

import com.example.machineest.data.network.GetUserDetailsApi
import com.example.machineest.data.network.GetUserListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    const val  baseURL="https://dummyjson.com/"

    @Singleton
    @Provides
    fun getUserListAPI(retrofit: Retrofit):GetUserListApi{
        return retrofit.create(GetUserListApi::class.java)
    }
    @Singleton
    @Provides
    fun getUserDetailsAPI(retrofit: Retrofit): GetUserDetailsApi {
        return retrofit.create(GetUserDetailsApi::class.java)
    }

    @Singleton
    @Provides
    fun  getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}