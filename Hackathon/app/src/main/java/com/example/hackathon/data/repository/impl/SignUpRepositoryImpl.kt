package com.example.hackathon.data.repository.impl

import com.example.hackathon.domain.request.SignupRequest
import com.example.hackathon.domain.response.AuthToken
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.data.datasource.AccountDataSource
import com.example.hackathon.data.repository.SignupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val accountDataSource: AccountDataSource) :
    SignupRepository {
    override suspend fun requestEmailAuth(email: String): Flow<BaseResponse> =
        accountDataSource.requestEmailAuth(email)

    override suspend fun checkCode(code: Int): Flow<BaseResponse> =
        accountDataSource.checkCode(code)

    override suspend fun signUpOwner(body: SignupRequest): Flow<DataResponse<AuthToken>> =
        accountDataSource.ownerSignup(body)

    override suspend fun signUpClient(body: SignupRequest): Flow<DataResponse<AuthToken>> =
        accountDataSource.clientSignup(body)
}