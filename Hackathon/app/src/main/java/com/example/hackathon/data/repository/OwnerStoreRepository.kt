package com.example.hackathon.data.repository

import com.example.hackathon.domain.request.owner.PatchStoreRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import com.example.hackathon.domain.response.BaseResponse
import retrofit2.Response

interface OwnerStoreRepository {

    suspend fun patchStore(
        token: String,
        storeId: Int,
        request: PatchStoreRequest
    ): Response<BaseResponse>

    suspend fun postStore(token: String, request: PostStoreRequest): Response<BaseResponse>
}