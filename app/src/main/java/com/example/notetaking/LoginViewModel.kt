package com.example.notetaking

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class LoginViewModel : ViewModel() {

    val navigateToRegister: MutableLiveData<Boolean> = MutableLiveData()
    val navigateToLogin: MutableLiveData<Boolean> = MutableLiveData()

    // Metode untuk menangani klik pada TextView "Belum punya akun? Register di sini"
    fun onRegisterClick() {
        navigateToRegister.value = true
    }
    fun onLoginClick() {
        navigateToRegister.value = false
        navigateToLogin.value = true

    }
}
