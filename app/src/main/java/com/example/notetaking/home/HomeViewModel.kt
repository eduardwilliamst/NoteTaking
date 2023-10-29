package com.example.notetaking.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notetaking.repository.HomeRepository

class HomeViewModel(
    private val local: HomeRepository,
) : ViewModel() {
    val notes = MutableLiveData<List<Note>>() // Gantilah dengan model data Anda

    val isEmpty: Boolean
        get() = notes.value.isNullOrEmpty()

    fun deleteNote(){

    }

    fun editNote(){

    }

    fun updateNote(title: String, content: String){

    }

    fun createNote(title: String, content: String){

    }
}
