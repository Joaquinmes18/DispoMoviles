package com.calyrsoft.ucbp1.features.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordScreen(
    vm: LoginViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()
    var email by remember { mutableStateOf("") }

    LaunchedEffect(state) {
        if (state is LoginViewModel.UiState.SuccessRecovery) {
            // mostrar mensaje y volver
            onBack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¿Olvidaste tu Contraseña?", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email de Registro") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { vm.recoverPassword(email) },
            enabled = state != LoginViewModel.UiState.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state == LoginViewModel.UiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
            }
            Text("Recuperar Contraseña")
        }

        if (state is LoginViewModel.UiState.Error) {
            Text((state as LoginViewModel.UiState.Error).message, color = Color.Red)
        }
    }
}
