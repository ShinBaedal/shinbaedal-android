package com.example.hackathon.data.api

import com.example.hackathon.domain.entity.Review
import com.example.hackathon.domain.request.PostReviewRequest
import com.example.hackathon.domain.response.BaseResponse
import com.example.hackathon.domain.response.DataResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewApi {
    @POST("review/{order_id}")
    suspend fun postReview(
        @Path("order_id") orderId: Int,
        @Body body: PostReviewRequest
    ): BaseResponse

    @POST("review/reply/{review_id}")
    suspend fun postReply(
        @Path("review_id") reviewId: Int,
        @Body content: String
    ): BaseResponse

    @GET("review/list/{store_id}")
    suspend fun getReviews(@Path("store_id") storeId: Int): DataResponse<List<Review>>
}