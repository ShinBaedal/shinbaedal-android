package com.example.hackathon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon.data.repository.MenuRepository
import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.response.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwnerMenuViewModel @Inject constructor(private val menuRepository: MenuRepository) :
    BaseViewModel() {
    val menuState = MutableLiveData<DataState<List<Menu>>>()
    private var _menuState = listOf<Menu>()

    fun getMenus(storeId: Long) {
        job = viewModelScope.launch {
            menuState.postValue(DataState.Loading)
            menuRepository.getMenus(storeId)
                .catch {
                    menuState.postValue(DataState.Failure(500, "서버와 연결을 실패했어요"))
                }
                .collect {
                    when (it.code) {
                        200 -> {
                            menuState.postValue(DataState.Success(it.data))
                        }
                        in 400..499 -> {
                            menuState.postValue(DataState.Failure(it.code, it.message))
                        }
                    }
                }
        }
    }
}