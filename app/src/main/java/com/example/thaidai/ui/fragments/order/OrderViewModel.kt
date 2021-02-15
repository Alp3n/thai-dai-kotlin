package com.example.thaidai.ui.fragments.order


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


import androidx.hilt.lifecycle.ViewModelInject

import androidx.lifecycle.ViewModel
import com.example.thaidai.backend.domain.item.Item

class OrderViewModel
@ViewModelInject
constructor(
): ViewModel() {

    val order: MutableList<Item?> = mutableStateListOf()

    fun addItem(item: Item?) {
        order.add(item)
    }
}


