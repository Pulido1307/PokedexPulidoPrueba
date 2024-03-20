package com.antonio.pulido.pokedexpulido.di

import com.antonio.pulido.pokedexpulido.repositories.PokeRepository
import com.antonio.pulido.pokedexpulido.repositories.PokeRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun pokeRepository(pokeRepositoryImp: PokeRepositoryImp): PokeRepository
}