package com.example.notetaking

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notetaking.home.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
    ],
    version = 1,
)
abstract class NoteDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "noteDB"
    }

    abstract fun noteDao(): NoteDao
}