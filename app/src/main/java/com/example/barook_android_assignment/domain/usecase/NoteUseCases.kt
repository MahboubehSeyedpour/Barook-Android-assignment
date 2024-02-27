package com.example.barook_android_assignment.domain.usecase

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote : GetNote,
    val searchNote : SearchNotes
)