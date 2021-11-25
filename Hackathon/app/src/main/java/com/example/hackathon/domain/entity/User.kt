package com.example.hackathon.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val id: Long, val name: String) : Parcelable
