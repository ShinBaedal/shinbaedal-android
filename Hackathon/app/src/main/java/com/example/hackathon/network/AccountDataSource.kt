package com.example.hackathon.network

import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.request.SignupClientRequest
import com.example.hackathon.domain.request.SignupOwnerRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountDataSource @Inject constructor(private val accountApi: AccountApi) {
    fun clientLogin(email: String, pw: String): Flow<BaseResponse> {
        return flow { emit(accountApi.loginClient(LoginRequest(email, pw))) }.flowOn(Dispatchers.IO)
    }

    fun ownerLogin(email: String, pw: String): Flow<BaseResponse> {
        return flow { emit(accountApi.loginClient(LoginRequest(email, pw))) }.flowOn(Dispatchers.IO)
    }

    fun clientSignup(email: String, pw: String, name: String, address: String): Flow<DataResponse<TokenResponse>> {
        return flow { emit(accountApi.signupClient(SignupClientRequest(email, pw, name, address))) }
            .flowOn(Dispatchers.IO)
    }

    fun ownerSignup(email: String, pw: String, name: String): Flow<DataResponse<TokenResponse>> {
        return flow { emit(accountApi.signupOwner(SignupOwnerRequest(email, pw, name))) }
            .flowOn(Dispatchers.IO)
    }

    fun ownerAutoLogIn():Flow<BaseResponse>{
        return flow { emit(accountApi.autoLoginOwner()) }.flowOn(Dispatchers.IO)
    }

    fun clientAutoLogIn():Flow<BaseResponse>{
        return flow { emit(accountApi.autoLoginClient()) }.flowOn(Dispatchers.IO)
    }
}