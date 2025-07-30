package com.example.tmdb.viewmodels

import com.example.tmdb.viewmodels.moviedetails.usecases.GetMovieUseCase
import com.example.tmdb.viewmodels.moviedetails.usecases.IGetMovieUseCase
import com.example.tmdb.viewmodels.movies.usecases.GetMoviesUseCase
import com.example.tmdb.viewmodels.movies.usecases.IGetMoviesUseCase
import com.example.tmdb.viewmodels.tv.usecases.GetTvsUseCase
import com.example.tmdb.viewmodels.tv.usecases.IGetTvsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {


    @Binds
    @ViewModelScoped
    abstract fun bindGetMoviesUseCase(
        impl: GetMoviesUseCase
    ): IGetMoviesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetTrendingTvsUseCase(
        impl: GetTvsUseCase
    ): IGetTvsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetMovieUseCase(
        impl: GetMovieUseCase
    ): IGetMovieUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindNetworkMonitor(
        impl: com.example.tmdb.network.NetworkMonitor
    ): com.example.tmdb.network.INetworkMonitor
}