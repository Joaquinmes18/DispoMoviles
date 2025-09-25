package com.calyrsoft.ucbp1.features.github.data.api

import com.calyrsoft.ucbp1.features.github.data.api.dto.GithubDto
import com.calyrsoft.ucbp1.features.github.domain.model.vo.Nickname
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface   GithubService {
    @GET("/users/{githubLogin}")
    suspend fun getInfoAvatar(@Path("githubLogin") githubLogin: Nickname): Response<GithubDto>
}