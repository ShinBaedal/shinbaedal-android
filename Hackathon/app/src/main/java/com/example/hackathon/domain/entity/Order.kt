package com.example.hackathon.domain.entity

data class Order(
    val id: Long,
    val storeName: String,
    val menuNames: String,
    var isDone: Boolean
)
