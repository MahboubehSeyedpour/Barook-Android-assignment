package com.example.barook_android_assignment.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.barook_android_assignment.R
import com.example.barook_android_assignment.data.db.model.Note
import com.example.barook_android_assignment.ui.theme.BoeingBlue
import com.example.barook_android_assignment.ui.theme.SoftGray

@Composable
fun NoteCard(
    modifier: Modifier,
    contentModifier: Modifier,
    note: Note,
    onDropDownItemClick: (Long) -> Unit
) {

    Card(modifier = modifier) {
        Column(modifier = contentModifier, verticalArrangement = Arrangement.SpaceBetween) {

            Row {
                Header(note, onDropDownItemClick)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                color = SoftGray,
                fontSize = 12.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                Modifier
                    .clip(CircleShape)
                    .background(BoeingBlue)
                    .size(10.dp)
                    .align(Alignment.End)
            )
        }
    }
}

@Composable
fun Header(note: Note, onDropDownItemClick: (Long) -> Unit) {

    var expanded by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {

        Text(
            text = note.title,
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier,
//                    .wrapContentSize(Alignment.TopStart),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "more",
                modifier = Modifier
//                        .align(Alignment.End)
                    .clickable { expanded = !expanded }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
            ) {
                DropdownMenuItem(
                    onClick = {
                        onDropDownItemClick(note.id)
                        expanded = false
                    },
                    text = { Text(text = stringResource(id = R.string.delete)) })
//                    text = { stringResource(id = R.string.delete) },
//                    onClick = { onDropDownItemClick(note.id) })
            }
        }
    }
}