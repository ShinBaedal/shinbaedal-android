package com.example.hackathon.data.api

import com.example.hackathon.domain.request.owner.PatchStoreRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import com.example.hackathon.domain.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*

interface OwnerStoreApi {

    @PATCH("store/{store_id}")
    suspend fun patchStore(
        @Path("store_id") storeId:Int,
        @Body request: PatchStoreRequest
    ): BaseResponse
    @POST("store")
    suspend fun postStore(
        @Body request: PostStoreRequest
    ):BaseResponse
}