package com.antonio.pulido.pokedexpulido.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "com.antonio.pulido.pokedexpulido")
    @Provides
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
        return applicationContext.dataStore
    }
}