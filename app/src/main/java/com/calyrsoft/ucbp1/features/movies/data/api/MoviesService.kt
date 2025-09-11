package com.calyrsoft.ucbp1.features.movies.data.api

import com.calyrsoft.ucbp1.features.github.data.api.dto.GithubDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {
    @GET("/3/discover/movie?sort_by=popularity.desc&api_key=fa3e844ce31744388e07fa47c7c5d8c3")
    suspend fun getInfoAvatar(@Path("githubLogin") githubLogin: String): Response<GithubDto>
}