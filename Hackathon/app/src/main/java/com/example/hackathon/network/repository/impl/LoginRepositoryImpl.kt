package com.example.hackathon.network.repository.impl

import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.network.datasource.AccountDataSource
import com.example.hackathon.network.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val accountDataSource: AccountDataSource) :
    LoginRepository {
    override suspend fun loginOwner(body: LoginRequest) = accountDataSource.ownerLogin(body)

    override suspend fun loginClient(body: LoginRequest): Flow<BaseResponse> = accountDataSource.clientLogin(body)

    override suspend fun autoLoginOwner(): Flow<BaseResponse> = accountDataSource.ownerAutoLogIn()

    override suspend fun autoLoginClient(): Flow<BaseResponse> = accountDataSource.clientAutoLogIn()
}