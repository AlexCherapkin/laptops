package com.example.laptops.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laptops.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arguments = intent.extras

        val name = arguments?.getString("KEY_NAME")
        val description = arguments?.getString("KEY_DESCRIPTION")
        val image = arguments?.getInt("KEY_ICON_RES_ID")
        val price = arguments?.getInt("KEY_PRICE")

        binding.toolbar.setNavigationOnClickListener {
           finish()
        }
    }
}