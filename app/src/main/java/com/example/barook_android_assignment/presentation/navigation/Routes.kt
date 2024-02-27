package com.example.barook_android_assignment.presentation.navigation

sealed class Routes(val route: String) {
    object Home: Routes("home")
    object Details: Routes("details")
}
