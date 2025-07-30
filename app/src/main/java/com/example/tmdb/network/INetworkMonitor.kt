package com.example.tmdb.network

import kotlinx.coroutines.flow.Flow

interface INetworkMonitor {
    suspend fun observeNetwork(): Boolean
}