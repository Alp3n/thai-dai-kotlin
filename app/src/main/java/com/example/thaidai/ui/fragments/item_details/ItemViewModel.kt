package com.example.thaidai.ui.fragments.item_details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thaidai.backend.domain.item.Item
import com.example.thaidai.backend.repository.ItemRepository
import kotlinx.coroutines.launch
import java.lang.Exception


const val STATE_KEY_ITEM = "state.key.itemId"

class ItemViewModel
@ViewModelInject
constructor(
    private val itemRepository: ItemRepository,
    @Assisted private val state: SavedStateHandle,
): ViewModel() {

    val item: MutableState<Item?> = mutableStateOf(null)

    init {
        //restoration on process dead
        state.get<String>(STATE_KEY_ITEM)?.let {itemId ->
            onTriggerEvent(ItemEvent.GetItemEvent(itemId))
        }
    }

    fun onTriggerEvent(event: ItemEvent) {
        viewModelScope.launch {
            try {
                when(event) {
                    is ItemEvent.GetItemEvent -> {
                        if(item.value == null) {
                            getItem(event.itemId)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("ERROR", "$e, ${e.cause}")
            }
        }
    }

   private suspend fun getItem(id: String) {
       val item = itemRepository.getOne(id = id)
       this.item.value = item

       state.set(STATE_KEY_ITEM, item.id)
   }
}