package com.example.tmdb.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


class NetworkMonitor(
    private val context: Context
) : INetworkMonitor {

    override suspend fun observeNetwork(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

}
