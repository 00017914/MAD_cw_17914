package com.example.mad_cw_17914.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mad_cw_17914.R
import com.example.mad_cw_17914.data.model.Cat
import com.example.mad_cw_17914.ui.viewmodel.CatViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCatScreen(viewModel: CatViewModel, onBack: () -> Unit) {
    // UI State for form fields
    var name by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var isVaccinated by remember { mutableStateOf(false) }
    var bio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("1") }

    // State for showing Notifications (Snackbars)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Requirement: Listen for success/error events from ViewModel
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_add_cat)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Requirement: Scrollable UI
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Field 1: Name (Required)
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.label_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Field 2: Breed
            OutlinedTextField(
                value = breed,
                onValueChange = { breed = it },
                label = { Text(stringResource(R.string.label_breed)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Field 3: Age (Numeric)
            OutlinedTextField(
                value = age,
                onValueChange = { if (it.all { c -> c.isDigit() }) age = it },
                label = { Text(stringResource(R.string.label_age)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            // Field 4: Gender (Selection)
            Text(stringResource(R.string.label_gender), style = MaterialTheme.typography.bodyLarge)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = gender == "Male", onClick = { gender = "Male" })
                Text("Male")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(selected = gender == "Female", onClick = { gender = "Female" })
                Text("Female")
            }

            // Field 5: Vaccinated (Boolean)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isVaccinated, onCheckedChange = { isVaccinated = it })
                Text(stringResource(R.string.label_vaccinated))
            }

            // Field 6: Description (Max 100 chars)
            OutlinedTextField(
                value = bio,
                onValueChange = { if (it.length <= 100) bio = it },
                label = { Text(stringResource(R.string.label_description)) },
                modifier = Modifier.fillMaxWidth(),
                supportingText = { Text("${bio.length}/100") }
            )

            // Stock (Mandatory for Update functionality)
            OutlinedTextField(
                value = stock,
                onValueChange = { if (it.all { c -> c.isDigit() }) stock = it },
                label = { Text(stringResource(R.string.label_stock)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // SAVE BUTTON
            Button(
                onClick = {
                    if (viewModel.validateCatInputs(name, breed, age, bio)) {
                        val newCat = Cat(
                            name = name,
                            breed = breed,
                            age = age.toIntOrNull() ?: 0,
                            gender = gender,
                            // API expects "1" or "0" for booleans in response,
                            // but accepts true/false in request
                            isVaccinated = isVaccinated,
                            bio = bio,
                            stockQuantity = stock.toIntOrNull() ?: 1
                        )
                        viewModel.addCat(newCat) {
                            onBack() // Only navigate back if save is successful
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_save))
            }

            // CANCEL BUTTON (Requirement: Clear manual navigation)
            OutlinedButton(
                onClick = { onBack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_cancel))
            }
        }
    }
}