package com.marc.rickmorty.core.di

import com.marc.rickmorty.features.characters.data.repository.CharacterRepositoryImpl
import com.marc.rickmorty.features.characters.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterRepository(
        implementation: CharacterRepositoryImpl
    ): CharacterRepository
}