package com.example.mad_cw_17914.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_17914.data.model.Cat
import com.example.mad_cw_17914.data.repository.CatRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CatViewModel(private val repository: CatRepository) : ViewModel() {

    // Requirement: State management for the list of products
    val cats = mutableStateListOf<Cat>()

    // LO4: SharedFlow to trigger Snackbars/Notifications in the UI
    private val _uiEvent = MutableSharedFlow<String>()
    val uiEvent = _uiEvent.asSharedFlow()

    // The student ID used across all API calls
    private val studentId = "00017914"

    /**
     * Requirement 4.2.1: View the list of all products (GET)
     * Handles the 'data' wrapper object returned by the WIUT API.
     */
    fun loadCats() {
        viewModelScope.launch {
            try {
                val response = repository.getCats()
                cats.clear()
                // The API returns a wrapper; we extract the list from .data
                cats.addAll(response.data)
            } catch (e: Exception) {
                Log.e("API_ERROR", "Failed to load cats: ${e.message}")
                _uiEvent.emit("Could not refresh list")
            }
        }
    }

    /**
     * Requirement 4.3.1: Create a new product (POST)
     */
    fun addCat(cat: Cat, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.insertCat(cat)
                if (response.isSuccessful) {
                    _uiEvent.emit("Cat added successfully!")
                    loadCats() // Refresh the list
                    onSuccess() // Navigate back to list screen
                } else {
                    _uiEvent.emit("Failed to save: ${response.message()}")
                }
            } catch (e: Exception) {
                _uiEvent.emit("Error: ${e.message}")
            }
        }
    }

    /**
     * Requirement 4.4.1: Update stock quantity (PUT)
     * Demonstrates partial record update by copying the existing object.
     */
    fun updateCatStock(catId: Int, newQty: Int) {
        viewModelScope.launch {
            try {
                val existingCat = cats.find { it.id == catId }
                existingCat?.let {
                    // Create an updated copy with the new stock value
                    val updatedCat = it.copy(stockQuantity = newQty)

                    val response = repository.updateQuantity(catId, updatedCat)
                    if (response.isSuccessful) {
                        _uiEvent.emit("Stock updated!")
                        loadCats() // Refresh UI
                    }
                }
            } catch (e: Exception) {
                _uiEvent.emit("Update failed")
            }
        }
    }

    /**
     * Requirement 4.5.1: Delete a product (DELETE)
     */
    fun deleteCat(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.removeCat(id)
                if (response.isSuccessful) {
                    _uiEvent.emit("Cat deleted successfully")
                    loadCats()
                }
            } catch (e: Exception) {
                _uiEvent.emit("Delete failed")
            }
        }
    }

    /**
     * Requirement: Input Validation logic
     * Ensures all mandatory fields are filled and valid before making network calls.
     */
    fun validateCatInputs(name: String, breed: String, age: String, bio: String): Boolean {
        // 1. Check for blank mandatory fields
        if (name.isBlank() || breed.isBlank() || age.isBlank()) {
            viewModelScope.launch { _uiEvent.emit("Please fill in all required fields") }
            return false
        }

        // 2. Age Validation (Must be a number and 0-30)
        val ageInt = age.toIntOrNull()
        if (ageInt == null) {
            viewModelScope.launch { _uiEvent.emit("Age must be a valid number") }
            return false
        }

        if (ageInt < 0) {
            viewModelScope.launch { _uiEvent.emit("Age cannot be negative") }
            return false
        }

        // NEW: Requirement - Age should not be more than 30
        if (ageInt > 30) {
            viewModelScope.launch { _uiEvent.emit("Age cannot be more than 30 years") }
            return false
        }

        // 3. Description length check
        if (bio.length > 100) {
            viewModelScope.launch { _uiEvent.emit("Description is too long (max 100)") }
            return false
        }

        return true
    }
}