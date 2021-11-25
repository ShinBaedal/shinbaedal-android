package com.example.hackathon.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hackathon.data.repository.FileRepository
import com.example.hackathon.data.repository.MenuRepository
import com.example.hackathon.data.repository.OwnerStoreRepository
import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.request.MenuRequest
import com.example.hackathon.domain.request.owner.PostStoreRequest
import com.example.hackathon.domain.response.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class OwnerStoreViewModel @Inject constructor(
    private val repository: OwnerStoreRepository,
    private val fileRepository: FileRepository,
    private val menuRepository: MenuRepository
) : BaseViewModel() {
    val fileUploadState = MutableLiveData<DataState<String>>()
    val postStoreState = MutableLiveData<DataState<String>>()


    fun postStore(
        name: String,
        address: String,
        category: String,
        photoUrl: String,
        tel: String,
        menus: List<MenuRequest>
    ) {
        job = viewModelScope.launch {
            repository.postStore(
                PostStoreRequest(name, address, category, photoUrl, tel, menus)
            ).catch {

            }.collect {

            }

        }
    }

    fun postFile(
        uri: File
    ) {
        fileUploadState.postValue(DataState.Loading)
        job = viewModelScope.launch {
            fileRepository.uploadPhoto(uri).collect {
                fileUploadState.postValue(DataState.Success(it.data.photoUrl))
            }
        }
    }


}