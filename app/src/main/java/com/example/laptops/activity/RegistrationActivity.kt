package com.example.laptops.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.laptops.databinding.ActivityRegistrationBinding

class RegistrationActivity : Activity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}