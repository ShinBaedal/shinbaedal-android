package com.example.hackathon.data.repository.impl

import com.example.hackathon.data.datasource.UserDataSource
import com.example.hackathon.data.repository.UserRepository
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.Me
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDataSource: UserDataSource):UserRepository {
    override suspend fun getMyInfo(): Flow<DataResponse<Me>> =userDataSource.getMyInfo()

    override suspend fun patchAddress(address:String): Flow<BaseResponse> =userDataSource.patchAddress(address)
}