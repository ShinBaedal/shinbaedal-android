package com.example.hackathon.network

import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.request.SignupClientRequest
import com.example.hackathon.domain.request.SignupOwnerRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

//로그인 회원가입
interface AccountApi {
    @POST("login/owner")
    suspend fun loginOwner(@Body body: LoginRequest): BaseResponse

    @POST("login/client")
    suspend fun loginClient(@Body body: LoginRequest): BaseResponse

    @POST("signup/owner")
    suspend fun signupOwner(@Body body:SignupOwnerRequest):DataResponse<TokenResponse>

    @POST("signup/client")
    suspend fun signupClient(@Body body:SignupClientRequest):DataResponse<TokenResponse>

    @POST("autologin/owner")
    suspend fun autoLoginOwner():BaseResponse

    @POST("autologin/client")
    suspend fun autoLoginClient():BaseResponse

}