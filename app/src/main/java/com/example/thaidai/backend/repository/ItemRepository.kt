package com.example.thaidai.backend.repository

import com.example.thaidai.backend.domain.item.Item

interface ItemRepository {

    suspend fun getList(query: String) : List<Item>

    suspend fun getOne(id: String) : Item
}