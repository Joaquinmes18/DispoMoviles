package com.calyrsoft.ucbp1.features.login.domain.repository

import com.calyrsoft.ucbp1.features.login.domain.model.LoginModel

interface ILoginRepository {
    suspend fun login(credentials: LoginModel): Result<Unit>
    suspend fun recoverPassword(email: String): Result<Unit>
}