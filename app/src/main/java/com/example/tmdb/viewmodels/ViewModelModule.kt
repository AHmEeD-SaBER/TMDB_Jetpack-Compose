package com.example.tmdb.viewmodels

import com.example.tmdb.viewmodels.main.MainViewModel
import com.example.tmdb.viewmodels.moviedetails.MovieDetailsViewModel
import com.example.tmdb.viewmodels.movies.MoviesViewModel
import com.example.tmdb.viewmodels.tv.TvViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { TvViewModel(get(), get()) }
}