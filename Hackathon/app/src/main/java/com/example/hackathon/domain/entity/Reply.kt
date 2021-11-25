package com.example.hackathon.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reply(
    val id: Long,
    val content: String
) : Parcelable
