package com.antonio.pulido.pokedexpulido.ui.favorites

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.pokedexpulido.repositories.local.LocalRepository
import com.antonio.pulido.pokedexpulido.ui.main.MainViewState
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    application: Application
) : BaseViewModel(application){
    init {
        initViewState(FavoritesViewState())
        getFavs()
    }

    fun onEvent(event: FavoritesViewEvent){
        when(event){
            is FavoritesViewEvent.OnSearchTextChange -> onSearchTexChange(event.search)
        }
    }

    private fun onSearchTexChange(search: String) {
        val state = currentViewState<FavoritesViewState>()
        val filter = state.pokefavs.filter { pokemon ->
            pokemon.name.contains(search, ignoreCase = true)
        }
        updateViewState(
            state.copy(
                searchText = search,
                pokefavsFilter = filter
            )
        )
    }

    private fun getFavs() = viewModelScope.launch{
        val state = currentViewState<FavoritesViewState>()
        val favs = localRepository.getPokemon()
        updateViewState(
            state.copy(
                pokefavs = favs,
                pokefavsFilter = favs
            )
        )
    }
}