package com.example.thaidai.ui.fragments

import android.os.Bundle
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
import com.example.thaidai.ui.reusable.ItemCard

class ItemsListFragment : Fragment() {

    fun handleOnClick() {
        findNavController().navigate(R.id.viewItemDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Items List",
                        style = TextStyle(
                            fontSize = TextUnit.Companion.Sp(24)
                        )
                    )
                    ItemCard(
                        itemNameEn = "Thai basil",
                        itemNameTh = "หกฟ่กๆไ",
                        itemNamePron = "krapao",
                        onClick = { handleOnClick() })
                    Spacer(modifier = Modifier.padding(16.dp))
                    Button(onClick = { handleOnClick() }) {
                        Text(text = "TO ITEM DETAILS")
                    }
                }
            }
        }
    }
}