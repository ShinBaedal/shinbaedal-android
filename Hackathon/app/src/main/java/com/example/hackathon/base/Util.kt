package com.example.hackathon.base

import android.net.Uri
import android.os.Environment
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun Uri.toFile(): File = File(this.path!!)

fun File.toMultipartBody(): MultipartBody.Part = MultipartBody.Part.createFormData(
    "name",
    "fileName",
    RequestBody.create("image/*".toMediaTypeOrNull(), this)
)
