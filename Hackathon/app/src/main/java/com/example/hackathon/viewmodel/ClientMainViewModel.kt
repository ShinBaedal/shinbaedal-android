package com.example.hackathon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hackathon.data.repository.StoreRepository
import com.example.hackathon.data.repository.UserRepository
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.domain.entity.Me
import com.example.hackathon.domain.entity.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class ClientMainViewModel(
    private val storeRepository: StoreRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {
    private val TAG = "ClientMainViewModel"

    val getStoresState = MutableLiveData<DataState<List<Store>>>()
    val myInfoState = MutableLiveData<Me>()


    fun getStores(address: String) {
        job = viewModelScope.launch {
            getStoresState.postValue(DataState.Loading)
            storeRepository.getStores(address)
                .catch {
                    Log.d(TAG, it.message.toString())
                    getStoresState.postValue(DataState.Failure(500, "서버 연결에 실패했어요"))
                }
                .collect {
                    getStoresState.postValue(DataState.Success(it.data))
                }
        }
    }

    fun getMyInfo() {
        job = viewModelScope.launch {
            userRepository.getMyInfo()
                .catch {
                    Log.d(TAG, it.message.toString())
                }
                .collect {
                    myInfoState.postValue(it.data!!)
                }
        }
    }

    fun patchAddress(address: String) {
        job = viewModelScope.launch {
            userRepository.patchAddress(address)
        }
    }
}