package com.example.hackathon.data.api

import com.example.hackathon.domain.response.DataResponse
import com.example.hackathon.domain.response.PhotoUrl
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileApi {
    @Multipart
    @POST("photo")
    suspend fun uploadPhoto(
        @Part photo: MultipartBody.Part
    ):DataResponse<PhotoUrl>


}