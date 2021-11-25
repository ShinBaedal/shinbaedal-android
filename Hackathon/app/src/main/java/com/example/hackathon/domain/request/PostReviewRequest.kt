package com.example.hackathon.domain.request

data class PostReviewRequest(val content: String, val rate: Int, val photoUrls: List<String>)
