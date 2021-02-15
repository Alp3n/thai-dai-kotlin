package com.example.thaidai

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {




    private val TAG: String = "Debug"

    @Inject
    lateinit var app: Application


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")


        Log.d(TAG, "USER ID: $userId")
        Log.d(TAG, "EMAIL: $emailId")
    }


}