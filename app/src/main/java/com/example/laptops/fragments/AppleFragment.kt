package com.example.laptops.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laptops.R
import com.example.laptops.activity.MainActivity
import com.example.laptops.adapter.ProductAdapter
import com.example.laptops.databinding.FragmentProductsBinding
import com.example.laptops.model.Product
import com.example.laptops.network.NetworkService
import com.example.laptops.onClickFlow
import com.example.laptops.onRefreshFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

class AppleFragment : Fragment(R.layout.fragment_products) {
    private lateinit var binding: FragmentProductsBinding

    companion object {
        fun newInstance() = AppleFragment()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductsBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).navigateToFragment(FirmsFragment.newInstance())
        }

        merge(
            flowOf(Unit),
            binding.swipeRefreshLayout.onRefreshFlow(),
            binding.buttonRefresh.onClickFlow()
        ).flatMapLatest { loadApple() }
            .distinctUntilChanged()
            .onEach {
                when (it) {
                    is ScreenState.DataLoaded -> {
                        setLoading(false)
                        setError(null)
                        setData(it.apple)
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

    @ExperimentalSerializationApi
    private fun loadApple() = flow {
        emit(ScreenState.Loading)
        val apple = NetworkService.loadApple()
        emit(ScreenState.DataLoaded(apple))
    }.catch {
        emit(ScreenState.Error(getString(R.string.error)))
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !rvProducts.isVisible
        swipeRefreshLayout.isRefreshing = isLoading && rvProducts.isVisible
    }

    private fun setData(apple: List<Product>?) = with(binding) {
        swipeRefreshLayout.isVisible = apple != null
        binding.rvProducts.layoutManager = LinearLayoutManager(context)
        rvProducts.adapter = ProductAdapter(
            apple ?: emptyList()
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
        data class DataLoaded(val apple: List<Product>) : ScreenState()
        object Loading : ScreenState()
        data class Error(val error: String) : ScreenState()
    }
}
