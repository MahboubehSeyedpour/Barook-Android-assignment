package com.example.barook_android_assignment.domain.usecase

import com.example.barook_android_assignment.data.db.model.Note
import com.example.barook_android_assignment.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Long): Note? {
        return repository.getNoteById(id)
    }
}