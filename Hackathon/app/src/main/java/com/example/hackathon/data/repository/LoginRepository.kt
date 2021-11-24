package com.example.hackathon.data.repository

import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.response.AuthToken
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginOwner(body: LoginRequest): Flow<DataResponse<AuthToken>>
    suspend fun loginClient(body: LoginRequest): Flow<DataResponse<AuthToken>>
}