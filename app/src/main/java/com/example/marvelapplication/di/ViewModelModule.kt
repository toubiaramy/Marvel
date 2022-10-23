package com.example.marvelapplication.di

import com.example.marvelapplication.data.characters.repository.CharacterRepository
import com.example.marvelapplication.data.characters.repository.CharacterRepositoryImpl
import com.example.marvelapplication.data.characters.repository.CharactersDao
import com.example.marvelapplication.retrofit.Api
import com.example.marvelapplication.vm.characterdetails.usecase.CharacterDetailsUseCase
import com.example.marvelapplication.vm.characterdetails.usecase.CharacterDetailsUseCaseImpl
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
    fun provideCharacterUseCase(api: Api, repository: CharacterRepository): CharacterUseCase {
        return CharacterUseCaseImpl(api, repository)
    }

    @Provides
    @ViewModelScoped
    fun provideCharacterDetailsUseCase(api: Api): CharacterDetailsUseCase {
        return CharacterDetailsUseCaseImpl(api)
    }

    @Provides
    @ViewModelScoped
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @ViewModelScoped
    fun provideCharactersRepository(dao: CharactersDao): CharacterRepository {
        return CharacterRepositoryImpl(dao)
    }
}