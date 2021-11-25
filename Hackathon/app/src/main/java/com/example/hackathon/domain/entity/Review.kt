package com.example.hackathon.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val id: Long,
    val menuNames: List<String>,
    val user: User,
    val content: String,
    val type: String,
    val storeName: String,
    val rate: Double,
    val reply: List<Reply>
) : Parcelable
