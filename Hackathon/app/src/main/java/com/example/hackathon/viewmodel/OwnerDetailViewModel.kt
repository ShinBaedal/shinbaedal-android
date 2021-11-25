package com.example.hackathon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon.data.repository.OwnerStoreRepository
import com.example.hackathon.data.repository.StoreRepository
import com.example.hackathon.domain.entity.Store
import com.example.hackathon.domain.response.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwnerDetailViewModel @Inject constructor(private val storeRepository: StoreRepository) :
    BaseViewModel() {
    private val TAG = "OwnerDetailViewModel"
    val storeState = MutableLiveData<DataState<Store>>()

    fun getStoreInfo(storeId: Long) {
        job = viewModelScope.launch {
            storeState.postValue(DataState.Loading)
            storeRepository.getStore(storeId)
                .catch {
                    Log.d(TAG, it.message.toString())
                    storeState.postValue(DataState.Failure(500, "서버 연결에 실패했어요"))
                }.collect {
                    storeState.postValue(DataState.Success(it.data))
                }
        }
    }

}