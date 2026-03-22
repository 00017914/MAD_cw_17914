package com.example.mad_cw_17914.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mad_cw_17914.R
import com.example.mad_cw_17914.ui.viewmodel.CatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(viewModel: CatViewModel, onNavigateToAdd: () -> Unit) {
    // State for the Delete Confirmation Dialog
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedCatId by remember { mutableStateOf<Int?>(null) }

    // Fetch data when the screen opens
    LaunchedEffect(Unit) {
        viewModel.loadCats()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.title_cat_list)) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add Cat")
            }
        }
    ) { padding ->
        // LazyColumn ensures the page is scrollable
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(viewModel.cats) { cat ->
                CatListItem(
                    cat = cat,
                    onUpdateStock = { newQty -> viewModel.updateCatStock(cat.id, newQty) },
                    onDeleteRequest = {
                        selectedCatId = cat.id
                        showDeleteDialog = true
                    }
                )
            }
        }

        // Requirement: Confirmation dialogue for deletion
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text(stringResource(R.string.msg_confirm_delete)) },
                text = { Text("This action cannot be undone.") },
                confirmButton = {
                    TextButton(onClick = {
                        selectedCatId?.let { viewModel.deleteCat(it) }
                        showDeleteDialog = false
                    }) { Text("Delete") }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) { Text("Cancel") }
                }
            )
        }
    }
}