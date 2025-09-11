package com.calyrsoft.ucbp1.features.movies.domain.usecase

import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movies.domain.repository.IMovieRepository
import kotlinx.coroutines.delay

class FindByIdUseCase(
    val repository: IMovieRepository
) {
    suspend fun invoke( id: Number): Result<MovieModel>{
        delay(5000)
        return repository.findById(id)
    }
}