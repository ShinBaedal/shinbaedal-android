package com.example.hackathon.base

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun Uri.toFile(): File = File(this.path!!)

fun Uri.toMultipartBody(): MultipartBody.Part = MultipartBody.Part.createFormData(
    "name",
    "fileName",
    RequestBody.create(MediaType.parse("image/*"), this.toFile())
)



