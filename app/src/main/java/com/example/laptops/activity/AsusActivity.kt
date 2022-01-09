package com.example.laptops.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laptops.adapter.ProductAdapter
import com.example.laptops.data.DataSource.products2
import com.example.laptops.databinding.ActivityProductsBinding

class AsusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding

    companion object {
        const val KEY_NAME = "name"
        const val KEY_DESCRIPTION = "description"
        const val KEY_ICON_RES_ID = "iconResId"
        const val KEY_PRICE = "price"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            val intent = Intent(this, FirmsActivity::class.java)
            startActivity(intent)
        }

        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter =
            ProductAdapter(products2) { (name, description, image, price) ->
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra(KEY_NAME, name)
                intent.putExtra(KEY_DESCRIPTION, description)
                intent.putExtra(KEY_ICON_RES_ID, image)
                intent.putExtra(KEY_PRICE, price)
                startActivity(intent)
            }
    }
}
