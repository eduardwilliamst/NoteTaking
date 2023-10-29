package com.example.notetaking

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notetaking.databinding.ActivityMainBinding
import com.example.notetaking.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private var binding: FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        })

        // Menghubungkan ViewModel dengan layout
        binding?.viewModel = viewModel

        // Mengatur lifecycle owner untuk Data Binding
        binding?.lifecycleOwner = viewLifecycleOwner

        // Menangani logika simpan ke SharedPreferences saat tombol Register diklik
        binding?.registerButton?.setOnClickListener {
            val username = binding?.usernameEditText?.text.toString()
            val password = binding?.passwordEditText?.text.toString()

            if (!username.isEmpty() && !password.isEmpty()) {
                // Mendapatkan referensi ke SharedPreferences
                val sharedPreferences = requireContext().getSharedPreferences("YourPrefsName", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                // Menyimpan data ke SharedPreferences
                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply()

                // Redirect ke halaman Login
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                Toast.makeText(requireContext(), "Username = $username and password = $password", Toast.LENGTH_SHORT).show()
            } else {
                // Handle case when username or password is empty
                Toast.makeText(requireContext(), "Username and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Membersihkan binding untuk mencegah memory leak
        binding = null
    }
}
