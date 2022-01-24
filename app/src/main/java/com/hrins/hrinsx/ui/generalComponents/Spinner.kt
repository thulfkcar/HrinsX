package com.hrins.hrinsx.ui.generalComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <T> Spinner(
    title: String,
    selectedItem:T?,
    defaultItemTitle: String,
    items: List<T>,
    onItemClicked: (T?) -> Unit,
    expandedState: MutableState<Boolean>
) {
    val selectedTitle = remember { mutableStateOf(defaultItemTitle) }
    if (selectedItem!=null)
        selectedTitle.value= selectedItem.toString()
    Box {
        OutlinedButton(
            onClick = { expandedState.value = true },
            Modifier.fillMaxWidth()
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "$title: ")
                    Text(text = selectedTitle.value)
                }

                Icon(Icons.Default.ArrowDropDown, contentDescription = "item")
            }
        }

        DropdownMenu(
            expanded = expandedState.value,
            onDismissRequest = { expandedState.value = false },

            ) {
            DropdownMenuItem(onClick = {
                onItemClicked(null)
                expandedState.value = false
                selectedTitle.value = defaultItemTitle
            }) {
                Text(defaultItemTitle)
            }
            items.forEach {
                DropdownMenuItem(onClick = {
                    onItemClicked(it)
                    expandedState.value = false
                    selectedTitle.value = it.toString()

                }

                ) {
                    Text(it.toString())
                }
            }
        }
    }
}