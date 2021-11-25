package com.example.hackathon.data.api

import com.example.hackathon.domain.request.owner.ReplyReviewRequest
import com.example.hackathon.domain.response.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewApi {

    @POST("review/reply/{review_id}")
    suspend fun replyReview(
        @Header("Authorization") token: String,
        @Path("review_id") reviewId: Int,
        @Body reply: ReplyReviewRequest


    ): Response<BaseResponse>
}