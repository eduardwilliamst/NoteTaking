package com.example.notetaking.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val notes = MutableLiveData<List<Note>>() // Gantilah dengan model data Anda

    val isEmpty: Boolean
        get() = notes.value.isNullOrEmpty()
}
