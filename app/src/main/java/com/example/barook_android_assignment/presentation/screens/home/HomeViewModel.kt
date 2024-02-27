package com.example.barook_android_assignment.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.barook_android_assignment.data.db.model.Note
import com.example.barook_android_assignment.domain.usecase.NoteUseCases
import com.example.barook_android_assignment.id
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    var events = MutableSharedFlow<HomeEvents>()
        private set

    var noteList by mutableStateOf(listOf<Note>())
    var searchText by mutableStateOf("")

    init {
        getNotes()
    }

    fun getNotes() {
        if (searchText.isBlank() || searchText.isEmpty()) {
            viewModelScope.launch {
                noteUseCases.getNotes().collect {
                    noteList = it
                }
            }
        } else {
            viewModelScope.launch {
                noteUseCases.searchNote(searchText).collect {
                    noteList = it
                }
            }
        }
    }

    fun onNoteClicked(id: Long) = viewModelScope.launch {
        events.emit(HomeEvents.NavigateToDetails(id))
    }

//    fun OnRestoreNoteClicked() {
//        viewModelScope.launch {
//            noteUseCases.addNote(recentDeletedNote ?: return@launch)
//
//            //null bcz if user call multiple times, same note can't be inserted again
//            recentDeletedNote = null
//        }
//    }

    fun onFABClicked() = viewModelScope.launch {
        events.emit(HomeEvents.NavigateToDetails(-1))
    }

    fun onSearchTextChange(value: String) {
        searchText = value
    }

    fun onDeleteNoteClicked(id: Long) {
        viewModelScope.launch {
            noteUseCases.deleteNote(id)
            events.emit(HomeEvents.ShowMessage("Note Deleted!"))
            getNotes()
        }
    }
}