package com.calyrsoft.ucbp1.features.dollar.domain.repository.github.data.datasource

import com.calyrsoft.ucbp1.features.github.data.api.GithubService
import com.calyrsoft.ucbp1.features.github.data.api.dto.GithubDto
import com.calyrsoft.ucbp1.features.github.domain.model.vo.Nickname

class GithubRemoteDataSource (val githubService: GithubService
){
    suspend fun getUser(nick: Nickname): Result<GithubDto> {
        val response = githubService.getInfoAvatar(nick)
        if (response.isSuccessful) {
            return Result.success(response.body()!!)
        } else {
            return Result.failure(Exception("Error al obtener el usuario"))
        }
    }

}