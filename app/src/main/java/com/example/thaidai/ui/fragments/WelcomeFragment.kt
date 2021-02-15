package com.example.thaidai.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.thaidai.LoginActivity
import com.example.thaidai.MainActivity
import com.example.thaidai.R
import com.google.firebase.auth.FirebaseAuth

class WelcomeFragment : Fragment() {

    fun handleSignIn() {
        findNavController().navigate(R.id.enterApplication)
    }

    fun handleLogOut() {
        //for logout
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply { 
            setContent {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Welcome to ThaiDai", modifier = Modifier.padding(16.dp))
                    Button(onClick = { handleSignIn()}, modifier = Modifier.padding(16.dp), colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF689f38),
                        contentColor = Color.White
                    )) {
                        Text(text = "Compose Order")
                    }
                    Button(onClick = { handleLogOut()}, modifier = Modifier.padding(16.dp), colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF689f38),
                        contentColor = Color.White
                    )) {
                        Text(text = "Log Out")
                    }
                }
            }
        }
    }
}