package com.example.notetaking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notetaking.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Menghubungkan ViewModel dengan layout
        binding.viewModel = viewModel

        // Mengatur lifecycle owner untuk Data Binding
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}