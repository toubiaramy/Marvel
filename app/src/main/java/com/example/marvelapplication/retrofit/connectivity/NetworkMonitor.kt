package com.example.toubiaapp.util.connectivity

interface NetworkMonitor {
    fun isConnected(): Boolean
    fun isInternetAvailable(): Boolean
}