package com.example.notetaking

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class LoginViewModel : ViewModel() {
    // Metode untuk menangani klik pada TextView "Belum punya akun? Register di sini"
    fun onRegisterClick(view: View) {
        val navController = Navigation.findNavController(view)

        // Melakukan navigasi ke RegisterFragment
        navController.navigate(R.id.action_loginFragment_to_registerFragment)
    }

}
