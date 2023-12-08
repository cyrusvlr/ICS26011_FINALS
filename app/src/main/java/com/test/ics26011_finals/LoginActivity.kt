package com.test.ics26011_finals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.ics26011_finals.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginDatabaseHelper: LoginDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginDatabaseHelper = LoginDatabaseHelper(this)

        binding.loginButton.setOnClickListener {
            val loginUsername = binding.loginUsername.text.toString()
            val loginPassword = binding.loginPassword.text.toString()
            loginDatabase(loginUsername, loginPassword)
        }
        binding.toSignupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginDatabase(username: String, password: String) {
        val userExists = loginDatabaseHelper.readUser(username, password)
        if (userExists) {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Login Failed.", Toast.LENGTH_LONG).show()
        }
    }
}