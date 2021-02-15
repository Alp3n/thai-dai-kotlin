package com.example.thaidai.ui.fragments.item_details

sealed class ItemEvent {
    data class GetItemEvent(
        val itemId: String
    ): ItemEvent()
}