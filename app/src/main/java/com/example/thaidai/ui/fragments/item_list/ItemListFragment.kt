package com.example.thaidai.ui.fragments.item_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.ComposeView

import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thaidai.R
import com.example.thaidai.backend.domain.item.Item
import com.example.thaidai.ui.fragments.order.OrderViewModel
import com.example.thaidai.ui.reusable.CategoryChip

import com.example.thaidai.ui.reusable.ItemCard


import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ItemListFragment : Fragment() {

    val viewModel: ItemListViewModel by viewModels()
    val orderVM: OrderViewModel by viewModels()
    fun handleOnClick(item: Item) {
        if(item.id != null) {
            // creating bundle to pass data to other fragment
            val bundle = Bundle()
            bundle.putString("itemId", item.id)
            // jetpack navigartion component that takes action and bundle
            findNavController().navigate(R.id.viewItemDetails, bundle)
        } else {
            // if something went wrong show toast
            Toast.makeText(activity,"Something went wrong", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val itemsList = viewModel.items.value
                val selectedCategory = viewModel.selectedCategory.value
                val order = orderVM.order



                Column() {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colors.background,
                        elevation = 6.dp
                    ) {
                        // Category list
                        Row(modifier = Modifier.fillMaxWidth()) {
                            ScrollableRow(modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                // All categories chip
                                CategoryChip(
                                    category = "ALL",
                                    isSelected = selectedCategory == null,
                                    onSelectedCategoryChange = {
                                        viewModel.onSelectedCategoryChange("")
                                    },
                                    onExecuteSearch = viewModel::getItemList)
                                // List of available categories
                                for(category in getAllCat()) {
                                    CategoryChip(
                                        category = category.value,
                                        isSelected = selectedCategory == category,
                                        onSelectedCategoryChange = {
                                            viewModel.onSelectedCategoryChange(it)
                                        },
                                        onExecuteSearch = viewModel::getItemList
                                    )
                                }
                            }
                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth().padding(8.dp),horizontalArrangement = Arrangement.Center ) {
                        ExtendedFloatingActionButton(
                            text = { Text(text = "Your Order") },
                            icon = { Icon(Icons.Filled.List) },
                            onClick = { findNavController().navigate(R.id.viewOrder) },
                            elevation = FloatingActionButtonDefaults.elevation(8.dp),
                            contentColor = Color.White,
                            backgroundColor = Color(0xFF689f38)
                        )
                    }

                    // Items list =>  LazyColumn is Jetpack Compose async component working similar to RecyclerView
                    LazyColumn() {
                        itemsIndexed(
                            // Delivering itemsList from mutableList from ViewModel
                            items = itemsList
                            // Assigning each item in itemsList to ItemCard component
                        ) { index, item ->
                            // Passing data through itemCard component
                            ItemCard(item = item, onClick = { handleOnClick(item) })
                        }
                    }


                }
            }
        }
    }
}