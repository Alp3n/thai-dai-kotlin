package com.example.thaidai.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.selection.SelectionContainer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.thaidai.R
import com.example.thaidai.backend.domain.item.Item
import com.example.thaidai.backend.network.ItemService
import com.example.thaidai.backend.network.responses.ItemListResponse
import com.example.thaidai.ui.reusable.ItemCard
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemsListFragment : Fragment() {

    var fetchedItemsList: ItemListResponse? = null

    fun handleOnClick() {
        findNavController().navigate(R.id.viewItemDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val service = Retrofit.Builder()
            .baseUrl("http://192.168.1.100:9000/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ItemService::class.java)

        CoroutineScope(IO).launch {
            fetchedItemsList = service.getItems()
            Log.d("MainAct", "onCreate: ${fetchedItemsList?.items}")
            cancel()
        }

        return ComposeView(requireContext()).apply {
            setContent {
                Column() {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Items List",
                        style = TextStyle(
                            fontSize = TextUnit.Companion.Sp(24)
                        )
                    )
                    LazyColumn(content = {
                        fetchedItemsList?.let {
                            itemsIndexed(it.items) { index, item ->
                                ItemCard(
                                    itemNameEn = item.names?.nameEn,
                                    itemNameTh = item.names?.nameTh,
                                    itemNamePron = item.names?.namePron,
                                    onClick = { handleOnClick() })
                            }
                        }
                    })

                    Spacer(modifier = Modifier.padding(16.dp))
                    Button(onClick = { handleOnClick() }) {
                        Text(text = "TO ITEM DETAILS")
                    }
                }
            }
        }
    }
}