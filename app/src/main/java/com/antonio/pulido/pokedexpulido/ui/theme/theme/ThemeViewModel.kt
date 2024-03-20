package com.antonio.pulido.pokedexpulido.ui.theme.theme

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.pokedexpulido.repositories.data.store.DataStoreRepository
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    application: Application
): BaseViewModel(application) {
    init {
        initViewState(ThemeViewState())
    }

    fun onEvent(event: ThemeViewEvent){
        when(event){
            ThemeViewEvent.GetTheme -> getTheme()
        }
    }

    private fun getTheme() = viewModelScope.launch{
        val state = currentViewState<ThemeViewState>()
        dataStoreRepository.getTheme().collect{
            updateViewState(
                state.copy(
                    isDarkMode = it
                )
            )
        }
    }
}