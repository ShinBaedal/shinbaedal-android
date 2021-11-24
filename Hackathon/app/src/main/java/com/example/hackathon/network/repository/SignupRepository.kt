package com.example.hackathon.network.repository

import com.example.hackathon.domain.request.SignupRequest
import com.example.hackathon.domain.response.AuthToken
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface SignupRepository {
    suspend fun requestEmailAuth(email: String): Flow<BaseResponse>
    suspend fun checkCode(code: Int): Flow<BaseResponse>
    suspend fun signUpOwner(body: SignupRequest): Flow<DataResponse<AuthToken>>
    suspend fun signUpClient(body: SignupRequest): Flow<DataResponse<AuthToken>>
}