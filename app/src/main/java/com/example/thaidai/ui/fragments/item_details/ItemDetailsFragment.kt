package com.example.thaidai.ui.fragments.item_details

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.thaidai.util.DEFAULT_IMAGE
import com.example.thaidai.util.loadPicture
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.thaidai.backend.domain.order.OrderLine
import com.example.thaidai.backend.domain.order.OrderListDAO
import com.example.thaidai.ui.fragments.order.OrderViewModel

@AndroidEntryPoint
class ItemDetailsFragment : Fragment() {

    private val viewModel: ItemViewModel by viewModels()

//    private val orderVM: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // getting id from the bundle and saving it in state
        arguments?.getString("itemId")?.let { resultId ->
            viewModel.onTriggerEvent(ItemEvent.GetItemEvent(resultId)) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                val item = viewModel.item.value
//                val order = orderVM.order


                fun handleButtonClick() {
//                    before i used Shared Prefrences to manage simple order list
//                    requireActivity().getSharedPreferences("order_list", Context.MODE_PRIVATE).edit()
//                        .apply{
//                            putString("order_line", item?.names?.nameTh)
//                        }.apply()
                    // PaperDB
                    val newItem = item?.let { OrderLine(it)}
                    if (newItem != null) {
                        OrderListDAO.addItem(newItem)
                        Toast.makeText(activity, "Added item ${item.names?.nameEn}", Toast.LENGTH_SHORT).show()
                    }
                }


                Log.d("DEBUG", "My item: $item")

                Column(modifier = Modifier.fillMaxWidth()) {
                    // Title
                    Surface(elevation = 6.dp, modifier = Modifier.fillMaxWidth(), color = Color(0xFF689f38)) {
                        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)) {
                            Text(
                                text = item?.names?.nameEn.toString(),
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(24)
                                ),
                                color = Color.White
                            )
                        }
                    }

                    ScrollableColumn() {
                        // Fetching image with async function
                        item?.image?.let { url ->

                            val image = loadPicture(url = url, defaultImage = DEFAULT_IMAGE)
                                .value

                            image?.let { img ->
                                Image(
                                    bitmap = img.asImageBitmap(),
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Description",
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(24)
                                )
                            )
                            Text(
                                text = if(item?.description !== null) {
                                    item?.description
                                } else "No description",
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(18)
                                )
                            )
                            Text(
                                text = "Allergens",
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(24)
                                )
                            )
                            Text(
                                text = if(item?.allergens !== null) {
                                    item?.allergens?.joinToString()
                                } else "No allergens",
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(18)
                                )
                            )
                            Text(
                                text = "Ingredients",
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(24)
                                )
                            )
                            Text(
                                text = if(item?.ingredients !== null) {
                                    item?.ingredients?.joinToString()
                                } else "No ingredients",
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(18)
                                )
                            )
                            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)) {
                                Button(
                                    onClick = {
                                        handleButtonClick()
                                    } ,
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0xFF689f38)
                                    )
                                ) {
                                    Text(text = "Add to order", color = Color.White)
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
