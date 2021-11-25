package com.example.hackathon.data.repository.impl

import android.net.Uri
import android.util.Log
import com.example.hackathon.base.toMultipartBody
import com.example.hackathon.data.datasource.FileDataSource
import com.example.hackathon.data.repository.FileRepository
import com.example.hackathon.domain.entity.PhotoUrl
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(private val fileDataSource: FileDataSource) :
    FileRepository {
    override suspend fun uploadPhoto(uri: File): Flow<DataResponse<PhotoUrl>> {
        Log.d("TAG","FileRepositoryImpl - uploadPhoto() called ${uri}")
        return fileDataSource.uploadPhoto(uri.toMultipartBody())
    }

}