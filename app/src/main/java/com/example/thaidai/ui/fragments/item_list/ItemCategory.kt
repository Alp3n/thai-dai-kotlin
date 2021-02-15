package com.example.thaidai.ui.fragments.item_list

enum class FoodCategory(val value: String) {
    RICE("rice"),
    NOODLES("noodles"),
    SOUP("soup"),
    DRINK("drink")
}

fun getAllCat(): List<FoodCategory> {
    return listOf(
        FoodCategory.RICE,
        FoodCategory.NOODLES,
        FoodCategory.SOUP,
        FoodCategory.DRINK
    )
}

fun getCat(value: String): FoodCategory? {
    val map = FoodCategory.values().associateBy(FoodCategory::value)
    return map[value]
}