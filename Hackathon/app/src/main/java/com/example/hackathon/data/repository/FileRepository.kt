package com.example.hackathon.data.repository

import android.net.Uri

interface FileRepository {
    suspend fun uploadPhoto(uri: Uri)
}