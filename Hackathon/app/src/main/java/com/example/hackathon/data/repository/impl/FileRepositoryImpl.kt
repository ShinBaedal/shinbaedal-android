package com.example.hackathon.data.repository.impl

import android.net.Uri
import com.example.hackathon.base.toMultipartBody
import com.example.hackathon.data.datasource.FileDataSource
import com.example.hackathon.data.repository.FileRepository
import com.example.hackathon.domain.entity.PhotoUrl
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(private val fileDataSource: FileDataSource) :
    FileRepository {
    override suspend fun uploadPhoto(uri: Uri): Flow<DataResponse<PhotoUrl>> =
        fileDataSource.uploadPhoto(uri.toMultipartBody())

}