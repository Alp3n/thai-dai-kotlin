package com.example.thaidai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister: Button = findViewById<Button>(R.id.register_regButton)
        val editEmail: EditText = findViewById<EditText>(R.id.register_emailEdit)
        val editPassword: EditText = findViewById<EditText>(R.id.register_passwordEdit)
        val btnLogin: TextView = findViewById<TextView>(R.id.register_login)


        // Setting register button onclick
        btnRegister.setOnClickListener {
            when {
                // Checking if inputs are empty
                TextUtils.isEmpty(editEmail.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(
                        this,"Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(editPassword.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show()
                }
                else -> {

                    // Creating object for firebase registration
                    val email: String = editEmail.text.toString().trim{ it<= ' '}
                    val password: String = editPassword.text.toString().trim{ it<= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->

                            // Successful registration
                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(this,"Registered successfully", Toast.LENGTH_SHORT).show()

                                // Creating new intent and passing user data to main activity
                                val intent = Intent(this, MainActivity::class.java)
                                // Deleting old activities from stack
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                // Failed registration
                                Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }


                }
            }

        }

        btnLogin.setOnClickListener {
            onBackPressed()
        }

    }
}