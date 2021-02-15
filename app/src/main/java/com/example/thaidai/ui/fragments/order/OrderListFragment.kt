package com.example.thaidai.ui.fragments.order

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment

import com.example.thaidai.backend.domain.order.OrderLine
import com.example.thaidai.backend.domain.order.OrderListDAO

import dagger.hilt.android.AndroidEntryPoint

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.internal.util.HalfSerializer.onNext

import java.util.*

@AndroidEntryPoint
class OrderListFragment : Fragment() {

//    private val viewModel: OrderViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                // loading order list from shared preferences
//                val orderList = requireActivity().getSharedPreferences("order_list", Context.MODE_PRIVATE)
//                    .getString("order_line", "Nothing...")
                var orderList = OrderListDAO.getOrder()


                    // Not working ;(
                    fun handleDelete(item: OrderLine, context: Context) {
                        OrderListDAO.removeItem(item, context)
                        Toast.makeText(
                            activity,
                            "Not working somehow ;/",
                            Toast.LENGTH_SHORT
                        ).show()

//                        orderList = OrderListDAO.getOrder()
                    }


                Column() {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFF689f38),
                        elevation = 6.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "Your Order",
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(24)
                                ),
                                color = Color.White
                            )
                        }
                    }

                    LazyColumn(modifier = Modifier.padding(24.dp)) {
                        itemsIndexed(
                            // Delivering itemsList from mutableList from ViewModel
                            items = orderList
                            // Assigning each item in itemsList to ItemCard component
                        ) { index, item ->
                            // Passing data through itemCard component
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${index + 1}. ${item.item.names?.nameTh}",
                                    style = TextStyle(
                                        fontSize = TextUnit.Sp(36)
                                    )
                                )
                                Text(
                                    text = "[${item.quantity}x]",
                                    style = TextStyle(
                                        fontSize = TextUnit.Sp(36)
                                    )
                                )
                                IconButton(onClick = {
                                    //handle on delete
                                    handleDelete(item, context)
                                }) {
                                    Icon(Icons.Filled.Delete)
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                OrderListDAO.clearOrder()
                                requireActivity().onBackPressed()
                                Toast.makeText(context, "Order list cleared", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF689f38),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Clear order",
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(24)
                                ),

                                )
                        }
                    }
                }
            }
        }
    }
}