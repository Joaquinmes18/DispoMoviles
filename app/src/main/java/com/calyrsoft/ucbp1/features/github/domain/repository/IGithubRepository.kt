package com.calyrsoft.ucbp1.features.github.domain.repository

import com.calyrsoft.ucbp1.features.github.domain.model.UserModel
import com.calyrsoft.ucbp1.features.github.domain.model.vo.Nickname

interface IGithubRepository {
    suspend fun findByNick(value: Nickname): Result<UserModel>
}