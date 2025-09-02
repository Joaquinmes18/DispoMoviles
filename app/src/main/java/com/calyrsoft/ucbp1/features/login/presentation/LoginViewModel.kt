package com.calyrsoft.ucbp1.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.login.domain.usecase.LoginUseCase
import com.calyrsoft.ucbp1.features.login.domain.usecase.RecoverPasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val recoverPasswordUseCase: RecoverPasswordUseCase
) : ViewModel() {

    sealed class UiState {
        object Init : UiState()
        object Loading : UiState()
        object SuccessLogin : UiState()
        object SuccessRecovery : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _state = MutableStateFlow<UiState>(UiState.Init)
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun doLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.Loading
            val result = loginUseCase(username, password)
            _state.value = result.fold(
                onSuccess = { UiState.SuccessLogin },
                onFailure = { UiState.Error(it.message ?: "Error en login") }
            )
        }
    }

    fun recoverPassword(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.Loading
            val result = recoverPasswordUseCase(email)
            _state.value = result.fold(
                onSuccess = { UiState.SuccessRecovery },
                onFailure = { UiState.Error(it.message ?: "Error en recuperación") }
            )
        }
    }
}