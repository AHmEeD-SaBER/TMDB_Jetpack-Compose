package com.example.tmdb.viewmodels.tv.usecases

import android.util.Log
import com.example.tmdb.data.tv.Tv
import com.example.tmdb.network.IRetrofitService

class GetTvsUseCase(private val api: IRetrofitService) : IGetTvsUseCase {

    override suspend fun getTvs(): Result<List<Tv>> {
        return try {
            val response = api.getTrendingTVShows()
            Log.d("GetTvsUseCase", "Response: ${response.body()}")
            if (response.isSuccessful) {
                Result.success(response.body()?.results ?: emptyList())
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

