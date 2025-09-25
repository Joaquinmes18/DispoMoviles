package com.calyrsoft.ucbp1.features.github.data.repository

import com.calyrsoft.ucbp1.features.github.data.datasource.GithubRemoteDataSource
import com.calyrsoft.ucbp1.features.github.domain.model.UserModel
import com.calyrsoft.ucbp1.features.github.domain.model.vo.Nickname
import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository

abstract class GithubRepository(val remoteDataSource: GithubRemoteDataSource
): IGithubRepository {
    override suspend fun findByNick(value: Nickname): Result<UserModel> {
        if (value.toString().isEmpty()) {
            return Result.failure(Exception("El campo no puede estar vacio"))
        }
        val response = remoteDataSource.getUser(value)


        response.fold(
            onSuccess = { it ->
                return Result.success(
                    UserModel(
                        nickname = it.login,
                        pathUrl = it.url
                    )
                )
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }
    }