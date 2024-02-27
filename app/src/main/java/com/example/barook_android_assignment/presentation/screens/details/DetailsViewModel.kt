package com.example.barook_android_assignment.presentation.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.barook_android_assignment.domain.repository.NoteRepository
import com.example.barook_android_assignment.id
import com.example.barook_android_assignment.presentation.screens.home.HomeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    var title by mutableStateOf("")
        private set

    var desc by mutableStateOf("")
        private set

    fun onTitleChanged(value: String) {
        title = value
    }

    fun onNoteChanged(value: String) {
        desc = value
    }

    fun onSaveAndBackClicked() {
        if ((title.isNotBlank() && title.isNotEmpty()) || (desc.isNotEmpty() && desc.isNotBlank())) {
            viewModelScope.launch(Dispatchers.IO) {
                noteRepository.addOrUpdateById(
                    com.example.barook_android_assignment.data.db.model.Note(
                        id = id++,
                        title = title,
                        content = desc,
                        colorId = 0
                    )
                )
            }
        }
    }
}