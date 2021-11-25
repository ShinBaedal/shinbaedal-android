package com.example.hackathon.data.repository.impl

import android.net.Uri
import com.example.hackathon.base.toMultipartBody
import com.example.hackathon.data.datasource.FileDataSource
import com.example.hackathon.data.repository.FileRepository
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(private val fileDataSource: FileDataSource) : FileRepository {
    override suspend fun uploadPhoto(uri: Uri) {
        fileDataSource.uploadPhoto(uri.toMultipartBody())
    }
}