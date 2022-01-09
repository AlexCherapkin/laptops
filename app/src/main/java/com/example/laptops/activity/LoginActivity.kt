package com.example.laptops.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.laptops.databinding.ActivityLoginBinding

class LoginActivity : Activity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonReg.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSignIn.setOnClickListener {
            val intent = Intent(this, FirmsActivity::class.java)
            startActivity(intent)
        }
    }
}