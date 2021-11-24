package com.example.hackathon.data.repository

import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.Me
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getMyInfo(): Flow<DataResponse<Me>>

    suspend fun patchAddress(address: String): Flow<BaseResponse>
}