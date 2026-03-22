package com.example.mad_cw_17914.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mad_cw_17914.data.model.Cat

@Composable
fun CatListItem(
    cat: Cat,
    onUpdateStock: (Int) -> Unit,
    onDeleteRequest: () -> Unit
) {
    // LO4: User-friendly GUI with clear information
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = cat.name,
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(onClick = onDeleteRequest) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Cat",
                        tint = Color.Red
                    )
                }
            }

            Text(text = "Breed: ${cat.breed}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Age: ${cat.age} years", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(12.dp))

            // Requirement: Update stock quantity buttons/inputs
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Stock: ${cat.stockQuantity}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (cat.stockQuantity == 0) Color.Red else Color.Unspecified
                )

                Spacer(modifier = Modifier.weight(1f))

                FilledTonalButton(
                    onClick = { onUpdateStock(cat.stockQuantity - 1) },
                    enabled = cat.stockQuantity > 0,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Text("-")
                }

                Spacer(modifier = Modifier.width(8.dp))

                FilledTonalButton(
                    onClick = { onUpdateStock(cat.stockQuantity + 1) },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Text("+")
                }
            }
        }
    }
}