package com.example.hackathon.data.api

import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.request.SignupRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.AuthToken
import retrofit2.http.Body
import retrofit2.http.POST

//로그인 회원가입
interface AccountApi {
    @POST("login/owner")
    suspend fun loginOwner(@Body body: LoginRequest): DataResponse<AuthToken>

    @POST("login/client")
    suspend fun loginClient(@Body body: LoginRequest): DataResponse<AuthToken>

    @POST("signup/owner")
    suspend fun signupOwner(@Body body: SignupRequest): DataResponse<AuthToken>

    @POST("signup/client")
    suspend fun signupClient(@Body body: SignupRequest): DataResponse<AuthToken>


    @POST("email")
    suspend fun requestEmailAuth(@Body email:String): BaseResponse

    @POST("code")
    suspend fun checkCode(@Body code: Int): BaseResponse

}