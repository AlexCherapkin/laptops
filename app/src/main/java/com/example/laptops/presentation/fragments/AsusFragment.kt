package com.example.laptops.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laptops.R
import com.example.laptops.presentation.activity.MainActivity
import com.example.laptops.presentation.adapter.ProductAdapter
import com.example.laptops.databinding.FragmentProductsBinding
import com.example.laptops.data.model.Product
import com.example.laptops.presentation.viewmodel.AsusViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

class AsusFragment : Fragment(R.layout.fragment_products) {
    private lateinit var binding: FragmentProductsBinding

    companion object {
        fun newInstance() = AsusFragment()
    }
    private val viewModel by lazy { AsusViewModel(requireContext(), lifecycleScope) }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductsBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).navigateToFragment(FirmsFragment.newInstance())
        }

        if (savedInstanceState == null) {
            viewModel.loadData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.loadData() }
        binding.buttonRefresh.setOnClickListener { viewModel.loadData() }
        viewModel.screenState.onEach {
            when (it) {
                is ScreenState.DataLoaded -> {
                    setLoading(false)
                    setError(null)
                    setData(it.asus)
                }
                is ScreenState.Error -> {
                    setLoading(false)
                    setError(it.error)
                    setData(null)
                }
                is ScreenState.Loading -> {
                    setLoading(true)
                    setError(null)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !rvProducts.isVisible
        swipeRefreshLayout.isRefreshing = isLoading && rvProducts.isVisible
    }

    private fun setData(asus: List<Product>?) = with(binding) {
        swipeRefreshLayout.isVisible = asus != null
        binding.rvProducts.layoutManager = LinearLayoutManager(context)
        rvProducts.adapter = ProductAdapter(
            asus ?: emptyList()
        ) { (name, description, image, price) ->
            (activity as MainActivity).navigateToFragment(
                DetailsFragment.newInstance(name, description, image, price)
            )
        }
    }

    private fun setError(message: String?) = with(binding) {
        errorLayout.isVisible = message != null
        tvError.text = message
    }

    sealed class ScreenState {
        data class DataLoaded(val asus: List<Product>) : ScreenState()
        object Loading : ScreenState()
        data class Error(val error: String) : ScreenState()
    }
}
