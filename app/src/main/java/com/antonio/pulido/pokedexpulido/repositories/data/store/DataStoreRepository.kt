package com.antonio.pulido.pokedexpulido.repositories.data.store

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setTheme(darkMode: Boolean)
    suspend fun getTheme(): Flow<Boolean>
}