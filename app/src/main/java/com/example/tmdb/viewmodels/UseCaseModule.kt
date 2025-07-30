package com.example.tmdb.viewmodels

import com.example.tmdb.network.INetworkMonitor
import com.example.tmdb.network.NetworkMonitor
import com.example.tmdb.viewmodels.moviedetails.usecases.GetMovieUseCase
import com.example.tmdb.viewmodels.moviedetails.usecases.IGetMovieUseCase
import com.example.tmdb.viewmodels.movies.usecases.GetMoviesUseCase
import com.example.tmdb.viewmodels.movies.usecases.IGetMoviesUseCase
import com.example.tmdb.viewmodels.tv.usecases.GetTvsUseCase
import com.example.tmdb.viewmodels.tv.usecases.IGetTvsUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val useCaseModule = module {
    factory<IGetMovieUseCase> { GetMovieUseCase(get()) }
    factory<IGetTvsUseCase> { GetTvsUseCase(get()) }
    factory<IGetMoviesUseCase> {
        GetMoviesUseCase(
            get(),
            get()
        )
    }

    single<INetworkMonitor> { NetworkMonitor(androidContext()) }
}