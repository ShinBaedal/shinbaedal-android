package com.example.hackathon.data.repository

import com.example.hackathon.domain.entity.Review
import com.example.hackathon.domain.request.PostReviewRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse

interface ReviewRepository {
    suspend fun postReview(orderId: Int, body: PostReviewRequest): BaseResponse

    suspend fun postReply(orderId: Int, content: String): BaseResponse


    suspend fun getReviews(orderId: Int): DataResponse<List<Review>>
}