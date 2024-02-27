package com.example.barook_android_assignment.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.barook_android_assignment.R
import com.example.barook_android_assignment.data.db.model.Note
import com.example.barook_android_assignment.domain.usecase.NoteUseCases
import com.example.barook_android_assignment.id
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    var events = MutableSharedFlow<HomeEvents>()
        private set

    private val _items: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val items: StateFlow<List<Note>> = _items

    private val _state = mutableStateOf<List<Note>>(listOf())
    val state: State<List<Note>> = _state

    private var recentDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    var noteList by mutableStateOf(listOf<Note>())

    init {
        getNotes()
    }

    private fun getNotes() {

        viewModelScope.launch {
            noteUseCases.getNotes().collect {
                noteList = it
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
        events.emit(HomeEvents.NavigateToDetails(id))
    }

    fun onDeleteNoteClicked(id: Long) {
        viewModelScope.launch {
            noteUseCases.deleteNote(id)
            events.emit(HomeEvents.ShowMessage("Note Deleted!"))
            getNotes()
        }
    }
}