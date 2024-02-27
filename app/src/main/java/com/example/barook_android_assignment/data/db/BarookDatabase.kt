package com.example.barook_android_assignment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.barook_android_assignment.data.db.model.Note

const val DATABASE_NAME = "barook.db"

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)

abstract class BarookDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
}