package com.example.marvelapplication.retrofit.client

import com.example.marvelapplication.MarvelApp
import com.example.marvelapplication.constant.API_KEY
import com.example.marvelapplication.constant.HASH
import com.example.marvelapplication.constant.PRIVATE_KEY
import com.example.marvelapplication.constant.PUBLIC_KEY
import com.example.marvelapplication.constant.TS
import com.example.marvelapplication.constant.TS_VALUE
import com.example.toubiaapp.util.connectivity.NetworkMonitorImpl
import com.example.toubiaapp.util.connectivity.NoConnectivityException
import com.example.toubiaapp.util.connectivity.NoInternetException
import java.math.BigInteger
import java.security.MessageDigest
import okhttp3.Interceptor
import okhttp3.OkHttpClient

object RetrofitClient {

    fun provideOkHttpClient(): OkHttpClient {
        val networkMonitor = NetworkMonitorImpl(MarvelApp.context)
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.addInterceptor { chain: Interceptor.Chain ->
            if (!networkMonitor.isConnected()) {
                throw NoConnectivityException()
            } else if (!networkMonitor.isInternetAvailable()) {
                throw NoInternetException()
            } else {
                val defaultRequest = chain.request()
                val hashSignature = (TS_VALUE + PRIVATE_KEY + PUBLIC_KEY).md5()
                val defaultHttpUrl = defaultRequest.url()
                val httpUrl = defaultHttpUrl.newBuilder()
                    .addQueryParameter(TS, TS_VALUE)
                    .addQueryParameter(API_KEY, PUBLIC_KEY)
                    .addQueryParameter(HASH, hashSignature)
                    .build()

                val requestBuilder = defaultRequest.newBuilder().url(httpUrl)

                return@addInterceptor chain.proceed(requestBuilder.build())
            }
        }.build()
    }

    fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }
}