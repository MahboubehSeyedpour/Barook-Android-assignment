package com.example.barook_android_assignment.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.barook_android_assignment.R
import com.example.barook_android_assignment.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithUserImage() {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = LightGray,
            titleContentColor = Color.Gray,
        ),
        title = {
            Text(
                text = stringResource(id = R.string.title),
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            Image(
                painter = painterResource(id = R.mipmap.ic_user_foreground),
                contentDescription = "icon",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithActionButtons(
    canPop: Boolean,
    navController: NavController,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                stringResource(id = R.string.save_and_back),
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal
            )
        },
        navigationIcon = {
            if (canPop) {
                IconButton(onClick = {
                    onBackClick()
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.save_and_back),
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    )
}