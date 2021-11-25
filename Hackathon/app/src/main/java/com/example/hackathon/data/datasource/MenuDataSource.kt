package com.example.hackathon.data.datasource

import com.example.hackathon.data.api.MenuApi
import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MenuDataSource @Inject constructor(private val menuApi: MenuApi) {
    suspend fun getMenu(storeId: Long): Flow<DataResponse<List<Menu>>> =
        flow { emit(menuApi.getMenus(storeId)) }.flowOn(Dispatchers.IO)
}