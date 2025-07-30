package com.example.tmdb.viewmodels.tv.usecases

import com.example.tmdb.data.tv.Tv

interface IGetTvsUseCase {
    suspend fun getTvs(): Result<List<Tv>>
}