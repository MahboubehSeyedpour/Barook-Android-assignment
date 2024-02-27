package com.example.barook_android_assignment.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.barook_android_assignment.data.db.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {


    //if we call INSERT function with an existing id, it will update the existing entry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<Note>>

    @Query("DELETE FROM notes WHERE id=:id")
    suspend fun deleteNoteById(id: Long)

    @Query("SELECT * FROM notes WHERE id=:id")
    suspend fun getNoteById(id: Long): Note
}