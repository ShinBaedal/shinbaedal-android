package com.example.hackathon.domain.request.owner

import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.request.MenuRequest

data class PostStoreRequest(
    val name: String,
    val address: String,
    val category: String,
    val photoUrl: String,
    val tel: String,
    val menus: List<MenuRequest>
)
