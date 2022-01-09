package com.example.laptops.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laptops.R
import com.example.laptops.activity.MainActivity
import com.example.laptops.adapter.ProductAdapter
import com.example.laptops.data.DataSource.products3
import com.example.laptops.databinding.FragmentProductsBinding

class DellFragment : Fragment(R.layout.fragment_products) {
    private lateinit var binding: FragmentProductsBinding

    companion object {
        fun newInstance() = DellFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductsBinding.bind(view)


        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).navigateToFragment(FirmsFragment.newInstance())
        }

        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter =
            ProductAdapter(products3) { (name, description, image, price) ->
                (activity as MainActivity).navigateToFragment(
                    DetailsFragment.newInstance(name, description, image, price)
                )
            }
    }
}
