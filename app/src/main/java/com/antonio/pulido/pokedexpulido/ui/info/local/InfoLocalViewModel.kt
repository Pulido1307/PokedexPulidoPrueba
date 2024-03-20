package com.antonio.pulido.pokedexpulido.ui.info.local

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.domain.entities.PokeEntity
import com.antonio.pulido.pokedexpulido.repositories.pokemon.local.LocalRepository
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoLocalViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    application: Application
) : BaseViewModel(application) {
    init {
        initViewState(InfoLocalViewState())
    }

    fun onEvent(event: InfoLocalViewEvent) {
        when (event) {
            is InfoLocalViewEvent.GetPokeInfo -> getPokemon(event.id)
        }
    }


    private fun getPokemon(id: Int) = viewModelScope.launch {
        val state = currentViewState<InfoLocalViewState>()
        val pokemon = localRepository.getPokemonById(id) ?: PokeEntity()
        updateViewState(
            state.copy(
                pokemon = pokemon,
                type = pokemon.types.split(",").map { it }
            )
        )
    }


}