package com.example.marvelapplication.di

import com.example.marvelapplication.retrofit.Api
import com.example.marvelapplication.vm.characters.usecase.CharacterUseCase
import com.example.marvelapplication.vm.characters.usecase.CharacterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideCharacterUseCase(api: Api): CharacterUseCase {
        return CharacterUseCaseImpl(api)
    }

    @Provides
    @ViewModelScoped
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}