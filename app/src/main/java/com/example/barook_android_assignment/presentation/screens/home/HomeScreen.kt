package com.example.barook_android_assignment.presentation.screens.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.barook_android_assignment.R
import com.example.barook_android_assignment.presentation.components.NoteCard
import com.example.barook_android_assignment.presentation.components.SearchBar
import com.example.barook_android_assignment.presentation.components.TopBarWithUserImage
import com.example.barook_android_assignment.presentation.navigation.Routes
import com.example.barook_android_assignment.ui.theme.BoeingBlue
import com.example.barook_android_assignment.ui.theme.LightGray
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect("events") {
        viewModel.events.collectLatest { event ->
            when (event) {
                is HomeEvents.NavigateToDetails -> navController.navigate("${Routes.Details.route}?id=${event.id}") {
                    launchSingleTop = true
                }

                is HomeEvents.ShowMessage -> coroutineScope.launch {
                    if (event.message.isNotBlank()) {
                        scaffoldState.snackbarHostState.showSnackbar(event.message)
                    }
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBarWithUserImage() },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier,
                shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 50)),
                containerColor = BoeingBlue,
                contentColor = LightGray,
                onClick = { viewModel.onFABClicked() }
            ) {
                Icon(Icons.Default.Add, contentDescription = "add note")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .padding(dimensionResource(id = R.dimen.grid_item_padding))
        ) {
            SearchBar(
                modifier = Modifier,
                query = "",
                shape = RoundedCornerShape(10.dp),
                hint = stringResource(id = R.string.search_bar_placeholder),
                onTextChanged = { },
                onDone = { }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Content(viewModel)

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content(viewModel: HomeViewModel) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = dimensionResource(id = R.dimen.grid_item_padding),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.grid_item_padding)),
        content = {

//            items(viewModel.items.value, key = { item -> item.id }) { item ->
            items(viewModel.noteList, key = { item -> item.id }) { item ->
                NoteCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .animateItemPlacement(
                            tween(durationMillis = 250)
                        )
                        .clickable { viewModel.onNoteClicked(item.id) },
                    contentModifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.note_padding)),
                    note = item,
                    onDropDownItemClick = { id -> viewModel.onDeleteNoteClicked(id) }
                )
            }
        }
    )
}