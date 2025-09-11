package com.calyrsoft.ucbp1.features.movies.data.api.dto

import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel

data class MoviesDto (val page:Number, val returns: List<MovieModel>)