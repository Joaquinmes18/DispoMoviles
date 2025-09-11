package com.calyrsoft.ucbp1.features.movies.domain.usecase

import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movies.domain.repository.IMovieRepository
import kotlinx.coroutines.delay

class FindByNameUseCase(
    val repository: IMovieRepository
) {
    suspend fun invoke(name:String):Result<MovieModel>{
        delay(5000)
        return repository.findByName(name)
    }
}

