package com.example.thaidai.backend.network

import com.example.thaidai.backend.domain.item.Item
import com.example.thaidai.backend.domain.item.Meat
import com.example.thaidai.backend.domain.item.Name
import com.example.thaidai.backend.domain.util.DomainMapper

class ItemDtoMapper : DomainMapper<ItemDto, Item> {

    override fun mapToDomainModel(dtoModel: ItemDto): Item {
        return Item(
            id = dtoModel.id,
            names = dtoModel.names ?: Name("null", "null", "null"),
            description = dtoModel.description,
            type = dtoModel.type,
            meats = dtoModel.meats ?: listOf(Meat()),
            allergens = dtoModel.allergens ?: listOf(),
//            attributes = networkModel.attributes ?: listOf(),
            spicy = dtoModel.spicy,
            egg = dtoModel.egg,
            sweet = dtoModel.sweet,
            ingredients = dtoModel.ingredients ?: listOf(),
            image = dtoModel.image
        )
    }

    override fun mapFromDomainModel(appModel: Item): ItemDto {
        return ItemDto(
            id = appModel.id,
            names = appModel.names,
            description = appModel.description,
            type = appModel.type,
            meats = appModel.meats ?: listOf(),
            allergens = appModel.allergens ?: listOf(),
//            attributes = appModel.attributes ?: listOf(),
            spicy = appModel.spicy,
            egg = appModel.egg,
            sweet = appModel.sweet,
            ingredients = appModel.ingredients ?: listOf(),
            image = appModel.image
        )
    }

    fun toDomainList(initial: List<ItemDto>): List<Item> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Item>): List<ItemDto> {
        return initial.map { mapFromDomainModel(it) }
    }

}