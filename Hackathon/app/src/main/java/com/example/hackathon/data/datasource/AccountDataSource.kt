package com.example.hackathon.data.datasource

import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.request.SignupRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.AuthToken
import com.example.hackathon.data.api.AccountApi
import com.example.hackathon.domain.request.EmailAuthRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountDataSource @Inject constructor(private val accountApi: AccountApi) {
    fun clientLogin(body: LoginRequest): Flow<DataResponse<AuthToken>> {
        return flow { emit(accountApi.loginClient(body)) }.flowOn(Dispatchers.IO)
    }

    fun ownerLogin(body: LoginRequest): Flow<DataResponse<AuthToken>> {
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


    fun requestEmailAuth(email: String): Flow<BaseResponse> {
        return flow { emit(accountApi.requestEmailAuth(email)) }.flowOn(Dispatchers.IO)
    }

    fun checkCode(email: String, code: String): Flow<BaseResponse> {
        return flow<BaseResponse> {
            emit(
                accountApi.checkCode(
                    EmailAuthRequest(
                        email,
                        code
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}