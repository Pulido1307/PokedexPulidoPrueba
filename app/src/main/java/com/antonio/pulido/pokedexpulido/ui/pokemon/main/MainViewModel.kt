package com.antonio.pulido.pokedexpulido.ui.pokemon.main

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.pokedexpulido.repositories.data.store.DataStoreRepository
import com.antonio.pulido.pokedexpulido.repositories.pokemon.remote.PokeRepository
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.antonio.pulido.web.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokeRepository: PokeRepository,
    private val dataRepository: DataStoreRepository,
    application: Application
) : BaseViewModel(application) {
    init {
        initViewState(MainViewState())

        getPokeList()
    }

    private fun getTheme() = viewModelScope.launch {
        val state = currentViewState<MainViewState>()
        dataRepository.getTheme().collect {
            updateViewState(
                state.copy(
                    isDarkMode = it
                )
            )
        }


    }

    fun onEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.OnSearchTextChange -> onSearchTexChange(event.search)
            is MainViewEvent.OnChageTheme -> onChageTheme()
        }
    }

    private fun onChageTheme() = viewModelScope.launch{
        val state = currentViewState<MainViewState>()
        dataRepository.setTheme(!state.isDarkMode)
        updateViewState(
            state.copy(
                isDarkMode = !state.isDarkMode
            )
        )
    }

    private fun onSearchTexChange(search: String) {
        val state = currentViewState<MainViewState>()
        val filter = state.pokemones.filter { pokemon ->
            pokemon.name.contains(search, ignoreCase = true)
        }
        updateViewState(
            state.copy(
                searchText = search,
                pokemonesFilter = filter
            )
        )
    }

    private fun getPokeList() = viewModelScope.launch {
        val state = currentViewState<MainViewState>()
        pokeRepository.getPokeList(limit = 902, offset = 0).collect {
            when (it) {
                is NetworkResult.Error -> {
                    onError("Ocurrió un error al conseguir la información de la PokeApi")
                    Log.i("TAG", "getPokeList: ${it.message}")
                }

                is NetworkResult.Loading -> onLoadingChange(true)
                is NetworkResult.Success -> {
                    updateViewState(
                        state.copy(
                            pokemones = it.data?.results ?: listOf(),
                            pokemonesFilter = it.data?.results ?: listOf(),

                            )
                    )

                    getTheme()
                }
            }
        }
    }

    private fun onError(msg: String) {
        onLoadingChange(false)
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun onLoadingChange(it: Boolean) {
        val state = currentViewState<MainViewState>()
        updateViewState(
            state.copy(isLoading = it)
        )
    }
}