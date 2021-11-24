package com.example.hackathon.network.repository

import com.example.hackathon.domain.response.DataState
import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.request.SignupRequest
import com.example.hackathon.domain.response.AuthToken
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginOwner(body: LoginRequest): Flow<BaseResponse>
    suspend fun loginClient(body: LoginRequest): Flow<BaseResponse>

    suspend fun autoLoginOwner(): Flow<BaseResponse>
    suspend fun autoLoginClient(): Flow<BaseResponse>



}