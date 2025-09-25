package com.calyrsoft.ucbp1.features.dollar.domain.repository.github.domain.usecase

import com.calyrsoft.ucbp1.features.github.domain.model.UserModel
import com.calyrsoft.ucbp1.features.github.domain.model.vo.Nickname
import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository
import kotlinx.coroutines.delay

class FindByNickNameUseCase(
    val repository: IGithubRepository
) {
    suspend fun invoke(nickname: Nickname) : Result<UserModel>  {
        delay(5000)
        return repository.findByNick(nickname)
    }
}