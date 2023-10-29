package com.example.notetaking.repository

import com.example.notetaking.home.Note

interface HomeRepository {
    suspend fun provideNotes(): List<Note>
    suspend fun addNote(note: String): List<Note>
    suspend fun updateNote(note: String, date: String): List<Note>
    suspend fun deleteNote(note: Note): List<Note>
    fun clearToken()
}