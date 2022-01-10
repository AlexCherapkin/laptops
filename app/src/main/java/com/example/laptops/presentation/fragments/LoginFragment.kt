package com.example.laptops.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.laptops.R
import com.example.laptops.presentation.activity.MainActivity
import com.example.laptops.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.buttonReg.setOnClickListener {
            (activity as MainActivity).navigateToFragment(RegistrationFragment.newInstance())
        }
        binding.buttonSignIn.setOnClickListener {
            (activity as MainActivity).navigateToFragment(FirmsFragment.newInstance())
        }
    }
}