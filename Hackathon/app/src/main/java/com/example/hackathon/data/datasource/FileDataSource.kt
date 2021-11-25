package com.example.hackathon.data.datasource

import com.example.hackathon.data.api.FileApi
import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.PhotoUrl
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class FileDataSource @Inject constructor(private val fileApi: FileApi) {
    suspend fun uploadPhoto(part: MultipartBody.Part) =
        flow<DataResponse<PhotoUrl>> { emit(fileApi.uploadPhoto(part)) }
}