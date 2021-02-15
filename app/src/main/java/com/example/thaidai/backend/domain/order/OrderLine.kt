package com.example.thaidai.backend.domain.order

import com.example.thaidai.backend.domain.item.Item

data class OrderLine (val item: Item, var quantity: Int = 0)