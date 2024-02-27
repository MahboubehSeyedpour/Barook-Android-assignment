package com.example.barook_android_assignment.presentation.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.barook_android_assignment.data.db.model.Note
import com.example.barook_android_assignment.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    var title by mutableStateOf("")
        private set

    var noteId by mutableStateOf(savedStateHandle.get<Long>("id")!!)
        private set

    var desc by mutableStateOf("")
        private set

    var note by mutableStateOf<Note?>(null)
        private set

    fun onTitleChanged(value: String) {
        title = value
    }

    fun onNoteChanged(value: String) {
        desc = value
    }

    init {
        getNote()
    }

    private fun getNote() {
        viewModelScope.launch {
            note = noteUseCases.getNote(noteId)
            title = note?.title ?: ""
            desc = note?.content ?: ""
        }
    }

    fun onSaveAndBackClicked() {
        if ((title.isNotBlank() && title.isNotEmpty()) || (desc.isNotEmpty() && desc.isNotBlank())) {
            viewModelScope.launch(Dispatchers.IO) {
                noteUseCases.addNote(
                    Note(
                        id = note?.id ?: 0,
                        title = title,
                        content = desc,
                        colorId = 0
                    )
                )
            }
        }
    }
}