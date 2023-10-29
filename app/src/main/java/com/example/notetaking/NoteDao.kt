package com.example.notetaking

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notetaking.home.NoteEntity

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note")
    suspend fun selectAllNotes(): List<NoteEntity>

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}