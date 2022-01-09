package com.example.laptops.fragments

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laptops.R
import com.example.laptops.activity.MainActivity
import com.example.laptops.adapter.ProductAdapter
import com.example.laptops.databinding.FragmentProductsBinding
import com.example.laptops.network.NetworkService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class AsusFragment : Fragment(R.layout.fragment_products) {
    private lateinit var binding: FragmentProductsBinding

    companion object {
        fun newInstance() = AsusFragment()
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        binding.rvProducts.adapter =
            ProductAdapter(emptyList()) {}
        binding.error.visibility = VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
        binding.progressBar.visibility = GONE
    }
    private val scope =
        CoroutineScope(Dispatchers.Main + SupervisorJob() + exceptionHandler)

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductsBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).navigateToFragment(FirmsFragment.newInstance())
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            loadAsus()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        loadAsus()
    }

    @ExperimentalSerializationApi
    private fun loadAsus() {
        scope.launch {
            val asus = NetworkService.loadAsus()
            binding.rvProducts.layoutManager = LinearLayoutManager(context)
            binding.rvProducts.adapter =
                ProductAdapter(asus) { (name, description, image, price) ->
                    (activity as MainActivity).navigateToFragment(
                        DetailsFragment.newInstance(name, description, image, price)
                    )
                }
            binding.progressBar.visibility = GONE
            binding.swipeRefreshLayout.isRefreshing = false
            binding.error.visibility = GONE
        }
    }
}
