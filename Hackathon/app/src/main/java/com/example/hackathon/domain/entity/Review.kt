package com.example.hackathon.domain.entity

data class Review(
    val id: Long,
    val menuNames: List<String>,
    val user: User,
    val content: String,
    val type: String,
    val storeName: String,
    val rate: Double,
    val reply: List<Reply>
)
