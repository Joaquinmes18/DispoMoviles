package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()

    when (val s = state.value) {
        is DollarViewModel.DollarUIState.Loading -> Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator() }

        is DollarViewModel.DollarUIState.Error -> Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { Text(s.message) }

        is DollarViewModel.DollarUIState.Success -> {
            DollarGrid(
                oficial = s.data.dollarOfficial,
                paralelo = s.data.dollarParallel,
                usdt = s.data.dollarUsdt,
                usdc = s.data.dollarUsdc,
                updatedAt = s.data.updatedAt
            )
        }
    }
}

@Composable
private fun DollarGrid(
    oficial: String?,
    paralelo: String?,
    usdt: String?,
    usdc: String?,
    updatedAt: Long?
) {
    Column(Modifier.padding(16.dp)) {
        Text("Tipos de cambio (Bs.)", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        val items = listOf(
            "Oficial" to (oficial ?: "-"),
            "Paralelo" to (paralelo ?: "-"),
            "USDT" to (usdt ?: "-"),
            "USDC" to (usdc ?: "-"),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items.size) { i ->
                RateCard(title = items[i].first, value = items[i].second)
            }
        }

        Spacer(Modifier.height(8.dp))
        updatedAt?.let {
            Text(
                "Actualizado: ${java.util.Date(it)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun RateCard(title: String, value: String) {
    ElevatedCard(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(6.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1
            )
        }
    }
}
