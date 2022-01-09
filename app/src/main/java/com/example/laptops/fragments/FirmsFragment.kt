package com.example.laptops.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.laptops.R
import com.example.laptops.activity.MainActivity
import com.example.laptops.databinding.FragmentFirmsBinding

class FirmsFragment : Fragment(R.layout.fragment_firms) {
    private lateinit var binding: FragmentFirmsBinding

    companion object {
        fun newInstance() = FirmsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirmsBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.apple.setOnClickListener {
            (activity as MainActivity).navigateToFragment(AppleFragment.newInstance())
        }
        binding.asus.setOnClickListener {
            (activity as MainActivity).navigateToFragment(AsusFragment.newInstance())
        }
        binding.dell.setOnClickListener {
            (activity as MainActivity).navigateToFragment(DellFragment.newInstance())
        }
    }
}