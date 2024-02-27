package com.example.barook_android_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.barook_android_assignment.presentation.navigation.Routes
import com.example.barook_android_assignment.presentation.screens.details.DetailsScreen
import com.example.barook_android_assignment.presentation.screens.home.HomeScreen
import com.example.barook_android_assignment.ui.theme.BarookAndroidAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), NavController.OnDestinationChangedListener {

    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            val navController = rememberNavController()
            navController.addOnDestinationChangedListener(this)


            BarookAndroidAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    BarookApp(navController, viewModel)
                }
            }
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
//        viewModel.setFABState(destination)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarookApp(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(modifier = Modifier) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = navController,
            startDestination = Routes.Home.route,
        ) {

            composable(route = Routes.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(
                route = "${Routes.Details.route}?id={id}",
                arguments = listOf(navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                })
            ) {
                DetailsScreen(navController = navController)
            }

        }
    }
}