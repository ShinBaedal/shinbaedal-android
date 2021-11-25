package com.example.hackathon.domain.entity

data class Order(
    val id: Int,
    val storeName: String,
    val menuNames: String,
    val isDone: Boolean
)
