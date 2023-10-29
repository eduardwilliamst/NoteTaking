package com.example.notetaking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notetaking.databinding.FragmentLoginBinding
import com.example.notetaking.home.HomeActivity

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
            if (shouldNavigate == true) {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                viewModel.navigateToRegister.value = false
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

            // Ambil data dari SharedPreferences
            val sharedPreferences = requireContext().getSharedPreferences("YourPrefsName", Context.MODE_PRIVATE)
            val savedUsername = sharedPreferences.getString("username", "")
            val savedPassword = sharedPreferences.getString("password", "")

            // Verifikasi login
            if (username == savedUsername && password == savedPassword) {
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                // Login gagal, tampilkan pesan kesalahan
                Toast.makeText(requireContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}
