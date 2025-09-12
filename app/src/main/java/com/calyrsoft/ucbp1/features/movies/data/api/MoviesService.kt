package com.calyrsoft.ucbp1.features.movies.data.api

import com.calyrsoft.ucbp1.features.movies.data.api.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("/3/discover/movie")
    suspend fun getPopularMovies(@Query("sort_by")sortBy:String,
                                 @Query("api_key")apiKey:String): Response<MoviesDto>
}