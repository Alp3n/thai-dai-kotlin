package com.example.thaidai.ui.fragments.item_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thaidai.backend.domain.item.Item
import com.example.thaidai.backend.repository.ItemRepository
import kotlinx.coroutines.launch


class ItemListViewModel
@ViewModelInject
constructor(
    private val repository: ItemRepository,
): ViewModel(){

    val items: MutableState<List<Item>> = mutableStateOf(listOf())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    init {
        getItemList()
    }

    // fetch function launched on new thread
    fun getItemList() {
        viewModelScope.launch {
            val result = repository.getList(
                query = query.value
            )
            items.value = result
        }
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }

    fun onSelectedCategoryChange(category: String) {
        val newCategory = getCat(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }
}