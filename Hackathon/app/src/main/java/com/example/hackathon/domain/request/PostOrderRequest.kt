package com.example.hackathon.domain.request

data class PostOrderRequest(val storeId: Long, val menuIds: List<Long>)
