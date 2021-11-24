package com.example.hackathon.network.datasource

import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.request.SignupRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.AuthToken
import com.example.hackathon.network.api.AccountApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountDataSource @Inject constructor(private val accountApi: AccountApi) {
    fun clientLogin(body: LoginRequest): Flow<BaseResponse> {
        return flow { emit(accountApi.loginClient(body)) }.flowOn(Dispatchers.IO)
    }

    fun ownerLogin(body: LoginRequest): Flow<BaseResponse> {
        return flow { emit(accountApi.loginClient(body)) }.flowOn(Dispatchers.IO)
    }

    fun clientSignup(
        signupRequest: SignupRequest
    ): Flow<DataResponse<AuthToken>> {
        return flow { emit(accountApi.signupClient(signupRequest)) }.flowOn(Dispatchers.IO)
    }

    fun ownerSignup(signupRequest: SignupRequest): Flow<DataResponse<AuthToken>> {
        return flow { emit(accountApi.signupOwner(signupRequest)) }
            .flowOn(Dispatchers.IO)
    }

    fun ownerAutoLogIn(): Flow<BaseResponse> {
        return flow { emit(accountApi.autoLoginOwner()) }.flowOn(Dispatchers.IO)
    }

    fun clientAutoLogIn(): Flow<BaseResponse> {
        return flow { emit(accountApi.autoLoginClient()) }.flowOn(Dispatchers.IO)
    }

    fun requestEmailAuth(email: String): Flow<BaseResponse> {
        return flow { emit(accountApi.requestEmailAuth(email)) }.flowOn(Dispatchers.IO)
    }

    fun checkCode(code: Int): Flow<BaseResponse> {
        return flow<BaseResponse> { emit(accountApi.checkCode(code)) }.flowOn(Dispatchers.IO)
    }
}