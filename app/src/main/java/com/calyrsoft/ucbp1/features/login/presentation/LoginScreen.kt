package com.calyrsoft.ucbp1.features.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    vm: LoginViewModel = koinViewModel(),
    onLoginOk: () -> Unit,
    onForgotPassword: () -> Unit,
    modifier: Modifier.Companion
) {
    val state by vm.state.collectAsState()
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    LaunchedEffect(state) {
        if (state is LoginViewModel.UiState.SuccessLogin) {
            onLoginOk()
        }
    }

    // Aquí replicas el diseño de la segunda imagen
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier.height(16.dp))

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Correo Electrónico") },
            singleLine = true,
            modifier = modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = modifier.fillMaxWidth()
        )

        Spacer(modifier.height(8.dp))
        Button(
            onClick = { vm.doLogin(user, pass) },
            enabled = state != LoginViewModel.UiState.Loading,
            modifier = modifier.fillMaxWidth()
        ) {
            if (state == LoginViewModel.UiState.Loading) {
                CircularProgressIndicator(modifier = modifier.size(18.dp))
                Spacer(modifier.width(8.dp))
            }
            Text("Iniciar Sesión")
        }

        Spacer(modifier.height(12.dp))
        TextButton(onClick = { onForgotPassword() }) {
            Text("Olvidé mi Contraseña")
        }

        if (state is LoginViewModel.UiState.Error) {
            Text((state as LoginViewModel.UiState.Error).message, color = Color.Red)
        }
    }
}
