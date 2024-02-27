package com.example.barook_android_assignment.data.repositoryImpl

import com.example.barook_android_assignment.data.db.BarookDatabase
import com.example.barook_android_assignment.data.db.model.Note
import com.example.barook_android_assignment.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val db: BarookDatabase
) : NoteRepository{
    override fun getNotes(): Flow<List<Note>> {
            return db.getNoteDao().getNotes()
    }

    override suspend fun getNoteById(id: Long): Note {
        return db.getNoteDao().getNoteById(id)
    }

    override suspend fun deleteNote(id: Long) {
        return db.getNoteDao().deleteNoteById(id)
    }

    override suspend fun addOrUpdateById(note: Note) {
        return db.getNoteDao().addNote(note)
    }
}
