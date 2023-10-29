package com.example.notetaking.local

import android.content.SharedPreferences
import com.example.notetaking.NoteDatabase
import com.example.notetaking.home.Note
import com.example.notetaking.home.NoteEntity
import com.example.notetaking.home.toNote
import com.example.notetaking.home.toNoteEntity
import com.example.notetaking.repository.HomeRepository
import com.example.notetaking.repository.LoginRepository
import kotlinx.coroutines.delay
import java.util.Date

class LocalRepository(
    private val sharedPreferences: SharedPreferences,
    private val noteDatabase: NoteDatabase,
) : HomeRepository, LoginRepository {

    companion object {
        private const val KEY_TOKEN = "token"
        const val PREF_NAME = "sharedPref"
    }

    override suspend fun provideNotes(): List<Note> {
        return noteDatabase.noteDao().selectAllNotes().map { noteEntity -> noteEntity.toNote() }
    }

    override suspend fun addNote(id: Int, title: String, content: String): List<Note> {
        noteDatabase.noteDao().insertNote(NoteEntity(id = id, title = title, content = content))
        return provideNotes()
    }

    override suspend fun updateNote(id: Int, title: String, content: String): List<Note> {
        noteDatabase.noteDao().insertNote(NoteEntity(id = id, title = title, content = content))
        return provideNotes()
    }

    override suspend fun deleteNote(note: Note): List<Note> {
        noteDatabase.noteDao().deleteNote(note.toNoteEntity())
        return provideNotes()
    }

    override fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }

    override suspend fun validateInput(username: String, password: String): Boolean {
        delay(3000)
        return username.isNotEmpty()
                && username.isNotBlank()
                && password.isNotEmpty()
                && password.isNotBlank()
    }

    override suspend fun authenticate(username: String, password: String): String {
        return if (username == "anangkur" && password == "123456") {
            "token"
        } else {
            throw UnsupportedOperationException("username dan password salah!")
        }
    }

    override suspend fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    override suspend fun loadToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }
}