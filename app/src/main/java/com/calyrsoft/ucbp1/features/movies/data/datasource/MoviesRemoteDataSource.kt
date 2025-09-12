package com.calyrsoft.ucbp1.features.movies.data.datasource

import com.calyrsoft.ucbp1.features.movies.data.api.MoviesService
import com.calyrsoft.ucbp1.features.movies.data.api.dto.MoviesDto

class MoviesRemoteDataSource (val movieservice: MoviesService) {
    suspend fun getMovie(id:Number): Result<MoviesDto> {
        val response = movieservice.getPopularMovies("popularity.desc", "fa3e844ce31744388e07fa47c7c5d8c3")

        if(response.isSuccessful) {
            return Result.success(response.body()!!)
        } else {
            return Result.failure(Exception("Error al obtener el usuario"))
        }
    }
}