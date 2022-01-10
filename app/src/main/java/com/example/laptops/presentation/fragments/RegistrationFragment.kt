package com.example.laptops.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.laptops.R
import com.example.laptops.presentation.activity.MainActivity
import com.example.laptops.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private lateinit var binding: FragmentRegistrationBinding

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)

        binding.buttonSignUp.setOnClickListener {
            (activity as MainActivity).navigateToFragment(LoginFragment.newInstance())
        }
    }
}