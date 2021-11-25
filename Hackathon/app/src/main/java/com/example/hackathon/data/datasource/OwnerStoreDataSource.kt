package com.example.hackathon.data.datasource

import com.example.hackathon.data.api.OwnerStoreApi
import com.example.hackathon.domain.request.owner.PatchStoreRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import com.example.hackathon.domain.response.BaseResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OwnerStoreDataSource @Inject constructor(private val api: OwnerStoreApi) {
    suspend fun patchStore(storeId: Int, request: PatchStoreRequest) =
        api.patchStore(storeId, request)

    suspend fun postStore(request: PostStoreRequest) = flow<BaseResponse> {
        emit(api.postStore(request))
    }
}