package com.example.hackathon.data.repository.impl

import com.example.hackathon.data.datasource.ReviewDataSource
import com.example.hackathon.data.repository.ReviewRepository
import com.example.hackathon.domain.entity.Review
import com.example.hackathon.domain.request.PostReviewRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(private val datasource:ReviewDataSource) : ReviewRepository {
    override suspend fun postReview(orderId: Int, body: PostReviewRequest): BaseResponse {
        return datasource.postReview(orderId, body)
    }

    override suspend fun postReply(orderId: Int, content: String): BaseResponse {
        return datasource.postReply(orderId, content)
    }

    override suspend fun getReviews(orderId: Int): DataResponse<List<Review>> {
        return datasource.getReviews(orderId)
    }

}