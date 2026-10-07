package com.marc.rickmorty.core.di

import com.marc.rickmorty.features.characters.data.repository.CharacterRepositoryImpl
import com.marc.rickmorty.features.characters.domain.repository.CharacterRepository
import com.marc.rickmorty.features.episode.data.repository.EpisodeRepositoryImpl
import com.marc.rickmorty.features.episode.domain.repository.EpisodeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(
        implementation: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    @Singleton
    abstract fun bindEpisodeRepository(
        implementation: EpisodeRepositoryImpl
    ): EpisodeRepository
}