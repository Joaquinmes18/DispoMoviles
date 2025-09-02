package com.calyrsoft.ucbp1.features.login.domain.usecase

import com.calyrsoft.ucbp1.features.login.domain.model.LoginModel
import com.calyrsoft.ucbp1.features.login.domain.repository.ILoginRepository

class LoginUseCase(
    private val repository: ILoginRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return repository.login(LoginModel(username, password))
    }
}