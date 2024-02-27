package com.example.barook_android_assignment.domain.usecase

import com.example.barook_android_assignment.data.db.model.Note
import com.example.barook_android_assignment.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        if ((note.title.isNotBlank() && note.title.isNotEmpty()) || (note.content.isNotEmpty() && note.content.isNotBlank())) {
            repository.addOrUpdateById(note)
        }
    }
}