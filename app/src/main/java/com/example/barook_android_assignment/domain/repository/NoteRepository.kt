package com.example.barook_android_assignment.domain.repository

import com.example.barook_android_assignment.data.db.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Long): Note?
    suspend fun deleteNote(id: Long)
    suspend fun addOrUpdateById(note: Note)
}