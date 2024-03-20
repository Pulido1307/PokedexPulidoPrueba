package com.antonio.pulido.pokedexpulido.repositories.data.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.antonio.pulido.pokedexpulido.util.PreferencesConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImp @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository{
    override suspend fun setTheme(darkMode: Boolean) {
        dataStore.edit {preferences->
            preferences[PreferencesConstants.IS_DARK_MODE] = darkMode
        }
    }

    override suspend fun getTheme(): Flow<Boolean> {
        return dataStore.data.map {
            it[PreferencesConstants.IS_DARK_MODE] ?: false
        }
    }
}