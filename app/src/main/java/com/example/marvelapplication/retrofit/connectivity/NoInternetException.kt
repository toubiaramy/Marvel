package com.example.toubiaapp.util.connectivity

import java.io.IOException

class NoInternetException : IOException() {
    override val message: String
        get() =
            "No internet available, please check your connected WIFi or Data"
}
