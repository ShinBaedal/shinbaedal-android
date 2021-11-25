package com.example.hackathon.data.repository.impl

import com.example.hackathon.data.datasource.MenuDataSource
import com.example.hackathon.data.repository.MenuRepository
import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.response.DataResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(private val menuDataSource: MenuDataSource) :
    MenuRepository {
    override suspend fun getMenus(storeId: Long): Flow<DataResponse<List<Menu>>> =
        menuDataSource.getMenu(storeId)

}