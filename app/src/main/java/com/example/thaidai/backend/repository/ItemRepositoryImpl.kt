package com.example.thaidai.backend.repository

import android.util.Log
import com.example.thaidai.backend.domain.item.Item
import com.example.thaidai.backend.network.ItemDtoMapper
import com.example.thaidai.backend.network.ItemService

class ItemRepositoryImpl(
    private val itemService: ItemService,
    private val mapper: ItemDtoMapper
) : ItemRepository {

    override suspend fun getList(query: String): List<Item> {
        val response = itemService.getItems(query = query).items
        return mapper.toDomainList(response)
    }

    override suspend fun getOne(id: String): Item {
        val response = itemService.getOneItem(id).item
        return mapper.mapToDomainModel(response)
    }
}