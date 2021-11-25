package com.example.hackathon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon.data.repository.ReviewRepository
import com.example.hackathon.domain.entity.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwnerReviewViewModel @Inject constructor(private val repository: ReviewRepository) :
    ViewModel() {

    val TAG = "review"

    private val _reviewData= MutableLiveData<List<Review>?>()
     val reviewData: MutableLiveData<List<Review>?>
        get()=_reviewData
    suspend fun getReview(orderId: Int) = viewModelScope.launch {

        try {
            repository.getReviews(orderId).let {
                when (it.code) {
                    200 -> {
                        _reviewData.value=it.data
                    }
                    in 400..499 -> {

                        Log.e(TAG, "getReview: ${it.message}")
                    }
                    else -> {

                    }
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "getReview error : ${e}")
        }
    }
}