package com.example.barook_android_assignment.presentation.screens.home

sealed class HomeEvents {
    class NavigateToDetails(val id: Long) : HomeEvents()
    class ShowMessage(val message: String) : HomeEvents()
}