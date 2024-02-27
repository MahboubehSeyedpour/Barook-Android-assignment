package com.example.barook_android_assignment.domain.usecase

import com.example.barook_android_assignment.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteNote(id)
    }
}