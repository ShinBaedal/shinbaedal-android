package com.example.hackathon.data.datasource

import com.example.hackathon.data.api.UserApi
import kotlinx.coroutines.flow.flow
import retrofit2.http.PATCH
import javax.inject.Inject

class UserDataSource @Inject constructor(private val userApi: UserApi) {
    suspend fun getMyInfo() = flow { emit(userApi.getMyInfo()) }

    @PATCH("my/address")
    suspend fun patchAddress(address: String) = flow { emit(userApi.patchAddress(address)) }

}