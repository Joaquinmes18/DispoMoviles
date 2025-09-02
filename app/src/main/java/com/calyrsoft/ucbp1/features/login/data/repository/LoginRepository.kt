package com.calyrsoft.ucbp1.features.login.data.repository

import com.calyrsoft.ucbp1.features.login.domain.model.LoginModel
import com.calyrsoft.ucbp1.features.login.domain.repository.ILoginRepository
import kotlinx.coroutines.delay

class LoginRepository : ILoginRepository {
    override suspend fun login(credentials: LoginModel): Result<Unit> {
        delay(1000) // simula red
        return if (credentials.username == "admin" && credentials.password == "1234") {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Credenciales inválidas"))
        }
    }

    override suspend fun recoverPassword(email: String): Result<Unit> {
        delay(1000) // simula red
        return if (email.contains("@")) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Correo inválido"))
        }
    }
}