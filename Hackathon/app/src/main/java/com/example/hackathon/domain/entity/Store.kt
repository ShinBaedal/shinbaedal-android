package com.example.hackathon.domain.entity

data class Store(
    val id: Long,
    val name: String,
    val category: String,
    val photoUrl: String,
    val tell: String,
    val address: String,
    val rate: Int
)
