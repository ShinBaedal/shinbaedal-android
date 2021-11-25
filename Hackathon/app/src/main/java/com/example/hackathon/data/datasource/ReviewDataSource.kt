package com.example.hackathon.data.datasource

import com.example.hackathon.data.api.ReviewApi
import com.example.hackathon.domain.entity.Review
import com.example.hackathon.domain.request.PostReviewRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import javax.inject.Inject

class ReviewDataSource @Inject constructor(private val api: ReviewApi) {

    suspend fun postReview(orderId: Int, body: PostReviewRequest) : BaseResponse {
      return  api.postReview(orderId, body)
    }

    suspend fun postReply(orderId: Int, content: String):BaseResponse {
        return api.postReply(orderId, content)
    }

    suspend fun getReviews(orderId: Int): DataResponse<List<Review>> {
       return api.getReviews(orderId)
    }


}