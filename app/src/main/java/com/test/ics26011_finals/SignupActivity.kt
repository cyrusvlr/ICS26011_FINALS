package com.test.ics26011_finals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.ics26011_finals.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var loginDatabaseHelper: LoginDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginDatabaseHelper = LoginDatabaseHelper(this)

        binding.signupButton.setOnClickListener {
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()
            signupDatabase(signupUsername, signupPassword)
        }
        binding.toLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signupDatabase(username: String, password: String) {
        val insertedRowId = loginDatabaseHelper.insertUser(username, password)
        if (insertedRowId != -1L) {
            Toast.makeText(this, "Sign up successful!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Sign up failed.", Toast.LENGTH_LONG).show()
        }
    }
}