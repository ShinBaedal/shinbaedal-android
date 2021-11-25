package com.example.hackathon.data.api

import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.entity.Me
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserApi {
    @GET("my")
    suspend fun getMyInfo(): DataResponse<Me>

    @PATCH("my/address")
    suspend fun patchAddress(@Body address: String): BaseResponse
}