package com.example.hackathon.data.api

import com.example.hackathon.domain.request.EmailAuthRequest
import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.request.SignupRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.AuthToken
import retrofit2.http.*

//로그인 회원가입
interface AccountApi {
    @POST("auth/login/owner")
    suspend fun loginOwner(@Body body: LoginRequest): DataResponse<AuthToken>

    @POST("auth/login/client")
    suspend fun loginClient(@Body body: LoginRequest): DataResponse<AuthToken>

    @POST("auth/signup/owner")
    suspend fun signupOwner(@Body body: SignupRequest): DataResponse<AuthToken>

    @POST("auth/signup/client")
    suspend fun signupClient(@Body body: SignupRequest): DataResponse<AuthToken>


    @GET("auth/email")
    suspend fun requestEmailAuth(@Query("email") email: String): BaseResponse

    @POST("auth/email")
    suspend fun checkCode(@Body body: EmailAuthRequest): BaseResponse

}