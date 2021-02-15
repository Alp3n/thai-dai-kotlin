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

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth = FirebaseAuth.getInstance();

        // Checking if user logged in before
        // If yes then it will just fetch data and proceed
        if (auth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("user_id", auth.currentUser!!.uid)
            startActivity(intent)
            finish()
        }

        val btnRegister: TextView = findViewById<TextView>(R.id.login_register)
        btnRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnLogin = findViewById<Button>(R.id.login_loginButton)
        val editEmail = findViewById<EditText>(R.id.login_emailEdit)
        val editPassword = findViewById<EditText>(R.id.login_passwordEdit)
        btnLogin.setOnClickListener {


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

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->

                            // Successful login
                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(this,"Logged in successfully", Toast.LENGTH_SHORT).show()

                                // Creating new intent and passing user data to main activity
                                val intent = Intent(this, MainActivity::class.java)
                                // Deleting old activities from stack
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                // Failed login
                                Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }


                }
            }
        }
    }


}