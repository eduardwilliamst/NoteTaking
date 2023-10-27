package com.example.notetaking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        viewModel.navigateToRegister.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        })

        // Menghubungkan ViewModel dengan layout
        binding.viewModel = viewModel

        // Mengatur lifecycle owner untuk Data Binding
        binding.lifecycleOwner = viewLifecycleOwner

        // Menangani logika login di sini saat tombol "Login" diklik
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Lakukan verifikasi login di sini
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
}
