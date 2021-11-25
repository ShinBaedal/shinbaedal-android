package com.example.hackathon.base

import android.net.Uri
import android.os.Environment
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun Uri.toFile(): File {
    val path = this.path
    return File(path, "DemoPicture.jpg")

}

fun Uri.toMultipartBody(): MultipartBody.Part = MultipartBody.Part.createFormData(
    "name",
    "fileName",
//    RequestBody.create("image/*", this.toFile())
)



