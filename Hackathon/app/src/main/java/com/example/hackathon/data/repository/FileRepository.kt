package com.example.hackathon.data.repository

import android.net.Uri
import com.example.hackathon.domain.entity.PhotoUrl
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface FileRepository {
    suspend fun uploadPhoto(uri: Uri): Flow<DataResponse<PhotoUrl>>
}