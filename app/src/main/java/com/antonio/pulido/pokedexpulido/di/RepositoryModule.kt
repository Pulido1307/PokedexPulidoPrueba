package com.antonio.pulido.pokedexpulido.di

import com.antonio.pulido.pokedexpulido.repositories.data.store.DataStoreRepository
import com.antonio.pulido.pokedexpulido.repositories.data.store.DataStoreRepositoryImp
import com.antonio.pulido.pokedexpulido.repositories.local.LocalRepository
import com.antonio.pulido.pokedexpulido.repositories.local.LocalRepositoryImp
import com.antonio.pulido.pokedexpulido.repositories.remote.PokeRepository
import com.antonio.pulido.pokedexpulido.repositories.remote.PokeRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun localUserRepository(localRepositoryImp: LocalRepositoryImp): LocalRepository
    @Binds
    abstract fun pokeRepository(pokeRepositoryImp: PokeRepositoryImp): PokeRepository
    @Binds
    abstract fun dataStoreRepository(dataStoreRepositoryImp: DataStoreRepositoryImp): DataStoreRepository
}