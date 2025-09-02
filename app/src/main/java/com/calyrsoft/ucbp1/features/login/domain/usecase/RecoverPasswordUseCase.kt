package com.calyrsoft.ucbp1.features.login.domain.usecase

import com.calyrsoft.ucbp1.features.login.domain.repository.ILoginRepository

class RecoverPasswordUseCase(
    private val repository: ILoginRepository
) {
    suspend operator fun invoke(email: String): Result<Unit> {
        return repository.recoverPassword(email)
    }
}