package com.example.hackathon.data.repository.impl

import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.response.AuthToken
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.data.datasource.AccountDataSource
import com.example.hackathon.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val accountDataSource: AccountDataSource) :
    LoginRepository {
    override suspend fun loginOwner(body: LoginRequest) = accountDataSource.ownerLogin(body)

    override suspend fun loginClient(body: LoginRequest): Flow<DataResponse<AuthToken>> = accountDataSource.clientLogin(body)

}