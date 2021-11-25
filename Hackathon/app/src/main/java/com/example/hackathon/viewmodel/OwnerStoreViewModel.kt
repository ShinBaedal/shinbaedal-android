package com.example.hackathon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hackathon.data.repository.OwnerStoreRepository
import com.example.hackathon.domain.request.owner.PatchStoreRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwnerStoreViewModel @Inject constructor(
    private val repository: OwnerStoreRepository
) : BaseViewModel() {

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    suspend fun patchStore(
        token: String,
        storeId: Int,
        name: String,
        category: String,
        photoUrl: String
    ) {
        job = viewModelScope.launch {

            val response =
                repository.patchStore(token, storeId, PatchStoreRequest(name, category, photoUrl))
            _isSuccess.value = response.isSuccessful
        }
    }

    suspend fun postStore(
        token: String,
        name: String,
        address: String,
        category: String,
        photoUrl: String,
        tel: String
    ) {
        job = viewModelScope.launch {
            val response =
                repository.postStore(
                    token,
                    PostStoreRequest(name, address, category, photoUrl, tel)
                )
            _isSuccess.value = response.isSuccessful
        }
    }


}