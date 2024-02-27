package com.example.barook_android_assignment.presentation.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.barook_android_assignment.R
import com.example.barook_android_assignment.presentation.components.TopBarWithActionButtons
import com.example.barook_android_assignment.ui.theme.GrayGoose
import com.example.barook_android_assignment.ui.theme.LightGray
import com.example.barook_android_assignment.ui.theme.Typography

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    var canPop by remember { mutableStateOf(false) }
    navController.addOnDestinationChangedListener { controller, _, _ ->
        canPop = controller.previousBackStackEntry != null
    }
    val keyboardController = LocalSoftwareKeyboardController.current


    Scaffold(
        topBar = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                TopBarWithActionButtons(
                    canPop = canPop,
                    navController = navController,
                    onBackClick = {
                        viewModel.onSaveAndBackClicked()
                    })
            }
        },
        backgroundColor = LightGray
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(dimensionResource(id = R.dimen.grid_item_padding))
        ) {
            Row {
                Title(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.title,
                    onTitleChanged = { value -> viewModel.onTitleChanged(value) },
                    hint = stringResource(id = R.string.new_note_title),
                    keyboardController
                )
            }


            Note(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.desc,
                onTextChanged = { value -> viewModel.onNoteChanged(value) },
                hint = stringResource(id = R.string.new_note_Desc),
                keyboardController
            )
        }
    }
}

@Composable
fun Title(
    modifier: Modifier,
    text: String,
    onTitleChanged: (text: String) -> Unit,
    hint: String,
    keyboardController: SoftwareKeyboardController?
) {
    OutlinedTextField(
        modifier = modifier,
        textStyle = Typography.bodyLarge,
        value = text,
        onValueChange = { value -> onTitleChanged(value) },
        placeholder = { Text(text = hint, color = GrayGoose, style = Typography.bodyLarge) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
        ),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
    )
}

@Composable
fun Note(
    modifier: Modifier,
    text: String,
    onTextChanged: (text: String) -> Unit,
    hint: String,
    keyboardController: SoftwareKeyboardController?
) {

    OutlinedTextField(
        modifier = modifier,
        value = text,
        textStyle = Typography.bodyMedium,
        onValueChange = { value -> onTextChanged(value) },
        placeholder = { Text(text = hint, color = GrayGoose, style = Typography.bodyLarge) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
    )
}