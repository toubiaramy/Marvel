package com.example.marvelapplication.di

import com.example.marvelapplication.constant.BASE_URL
import com.example.marvelapplication.retrofit.Api
import com.example.marvelapplication.retrofit.client.RetrofitClient.provideOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiInterface(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .build().create(Api::class.java)
    }
}