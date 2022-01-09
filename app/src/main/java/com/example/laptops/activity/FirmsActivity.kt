package com.example.laptops.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laptops.databinding.ActivityFirmsBinding

class FirmsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apple.setOnClickListener {
            val intent = Intent(this, AppleActivity::class.java)
            startActivity(intent)
        }
        binding.asus.setOnClickListener {
            val intent = Intent(this, AsusActivity::class.java)
            startActivity(intent)
        }
        binding.dell.setOnClickListener {
            val intent = Intent(this, DellActivity::class.java)
            startActivity(intent)
        }
    }
}