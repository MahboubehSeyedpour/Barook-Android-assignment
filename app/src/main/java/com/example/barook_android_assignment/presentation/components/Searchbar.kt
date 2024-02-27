package com.example.barook_android_assignment.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.barook_android_assignment.ui.theme.Gray
import com.example.barook_android_assignment.ui.theme.SoftGray

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    modifier: Modifier,
    query: String,
    onTextChanged: (text: String) -> Unit,
    onDone: () -> Unit,
    hint: String,
    shape: RoundedCornerShape
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = query,
            onValueChange = {
                onTextChanged(it)
            },
            placeholder = {
                androidx.compose.material.Text(
                    text = hint,
                    color = SoftGray,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 13.sp
                    )
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text).copy(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onDone()
                keyboardController?.hide()
            }),
            shape = shape,
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "search", tint = SoftGray)
            },
            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                backgroundColor = Gray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}