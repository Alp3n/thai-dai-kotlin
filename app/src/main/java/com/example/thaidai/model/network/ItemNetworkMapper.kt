package com.example.thaidai.model.network

import com.example.thaidai.model.app.item.Item
import com.example.thaidai.model.app.item.Meat
import com.example.thaidai.model.app.item.Name
import com.example.thaidai.model.app.util.ModelMapper

class ItemNetworkMapper : ModelMapper<ItemNetwork, Item> {

    override fun mapFromNetworkModel(networkModel: ItemNetwork): Item {
        return Item(
            id = networkModel.id,
            names = networkModel.names ?: Name(),
            description = networkModel.description,
            type = networkModel.type,
            meats = networkModel.meats ?: listOf(Meat()),
            allergens = networkModel.allergens ?: listOf(),
//            attributes = networkModel.attributes ?: listOf(),
            spicy = networkModel.spicy,
            egg = networkModel.egg,
            sweet = networkModel.sweet,
            ingredients = networkModel.ingredients ?: listOf(),
            image = networkModel.image
        )
    }

    override fun mapToNetworkModel(appModel: Item): ItemNetwork {
        return ItemNetwork(
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

    fun fromNetworkModelList(initial: List<ItemNetwork>): List<Item> {
        return initial.map { mapFromNetworkModel(it) }
    }

    fun toNetworkModelList(initial: List<Item>): List<ItemNetwork> {
        return initial.map { mapToNetworkModel(it) }
    }

}