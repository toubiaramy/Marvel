package com.example.toubiaapp.util.connectivity

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() =
            "No network available, please check your WiFi or Data connection"
}
