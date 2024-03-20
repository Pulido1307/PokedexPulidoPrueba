package com.antonio.pulido.pokedexpulido.ui.main

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.pokedexpulido.repositories.PokeRepository
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.antonio.pulido.web.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokeRepository: PokeRepository,
    application: Application
) : BaseViewModel(application) {
    init {
        initViewState(MainViewState())
        getPokeList()
    }
    
    fun onEvent(event: MainViewEvent){
        when(event){
            is MainViewEvent.onSearchTextChange -> onSearchTexChange(event.search)
        }
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
        pokeRepository.getPokeList(limit = 151, offset = 0).collect {
            when (it) {
                is NetworkResult.Error -> {
                    onError("Ocurrió un error al conseguir la información de la PokeApi")
                    Log.i("TAG", "getPokeList: ${it.message}")
                }

                is NetworkResult.Loading -> onLoadingChange(true)
                is NetworkResult.Success -> {
                    updateViewState(
                        state.copy(
                            pokemones = it.data?.results?: listOf(),
                            pokemonesFilter =  it.data?.results?: listOf()
                        )
                    )
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