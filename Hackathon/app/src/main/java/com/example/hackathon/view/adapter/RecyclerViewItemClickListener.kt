package com.example.hackathon.view.adapter

interface RecyclerViewItemClickListener<T> {
    fun onclick(data: T): Unit
}