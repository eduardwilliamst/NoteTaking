package com.example.notetaking.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoteViewHolder: ViewModel() {

    private val noteList: MutableList<Note> = mutableListOf()

    private val _note = MutableLiveData<List<Note>>()

    val notes: LiveData<List<Note>> = _note

    private val _progres = MutableLiveData<Boolean>()

    val progress: LiveData<Boolean> = _progres

    fun provideData() {

        viewModelScope.launch(Dispatchers.IO) {
            _progres.postValue(true)

            delay(1000)
        }
    }

    fun fetchData(): List<Note> {
        return if (this.noteList.isEmpty()) {
            emptyList()
        } else {
            this.noteList
        }
    }
}