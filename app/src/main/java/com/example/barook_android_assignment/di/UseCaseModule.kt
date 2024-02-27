package com.example.barook_android_assignment.di

import com.example.barook_android_assignment.domain.repository.NoteRepository
import com.example.barook_android_assignment.domain.usecase.AddNote
import com.example.barook_android_assignment.domain.usecase.DeleteNote
import com.example.barook_android_assignment.domain.usecase.GetNote
import com.example.barook_android_assignment.domain.usecase.GetNotes
import com.example.barook_android_assignment.domain.usecase.NoteUseCases
import com.example.barook_android_assignment.domain.usecase.SearchNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideNoteUseCases(repository: NoteRepository) =
        NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository),
            searchNote = SearchNotes(repository)
        )
}