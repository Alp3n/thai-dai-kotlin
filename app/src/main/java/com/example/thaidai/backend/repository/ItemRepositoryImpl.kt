package com.example.thaidai.backend.repository

import com.example.thaidai.backend.domain.item.Item
import com.example.thaidai.backend.network.ItemDtoMapper
import com.example.thaidai.backend.network.ItemService

class ItemRepositoryImpl(
    private val itemService: ItemService,
    private val mapper: ItemDtoMapper
) : ItemRepository {

    override suspend fun getList(): List<Item> {
        val response = itemService.getItems().items
        return mapper.toDomainList(response)
    }

    override suspend fun getOne(id: String): Item {
        val response = itemService.getOneItem(id).item
        return mapper.mapToDomainModel(response)
    }
}