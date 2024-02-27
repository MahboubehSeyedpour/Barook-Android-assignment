package com.example.barook_android_assignment.domain.usecase

import com.example.barook_android_assignment.data.db.model.Note
import com.example.barook_android_assignment.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}