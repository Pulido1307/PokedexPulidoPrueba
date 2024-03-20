package com.antonio.pulido.pokedexpulido.ui.info.remote

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.domain.models.response.info.PokeInfoResponse
import com.antonio.pulido.pokedexpulido.repositories.pokemon.local.LocalRepository
import com.antonio.pulido.pokedexpulido.repositories.pokemon.remote.PokeRepository
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.antonio.pulido.web.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoPokemonViewModel @Inject constructor(
    private val pokeRepository: PokeRepository,
    private val localRepository: LocalRepository,
    application: Application
) : BaseViewModel(application) {
    init {
        initViewState(InfoPokemonViewState())
    }

    fun onEvent(event: InfoPokemonViewEvent) {
        when (event) {
            is InfoPokemonViewEvent.GetPokeInfo -> getPokeInfo(event.id)
            InfoPokemonViewEvent.AddFavoritePokemon -> addFavoritePokemon()
            InfoPokemonViewEvent.RemoveFavoritePokemon -> removeFavoritePokemon()
        }
    }

    private fun removeFavoritePokemon() = viewModelScope.launch{
        Toast.makeText(getApplication(), "Se elimino de favoritos", Toast.LENGTH_SHORT).show()
        val state = currentViewState<InfoPokemonViewState>()
        state.pokeInfo.let {
            localRepository.deletePokemonById(it.id)
            updateViewState(
                state.copy(
                    isFav = false
                )
            )
        }

    }

    private fun addFavoritePokemon() = viewModelScope.launch {
        val state = currentViewState<InfoPokemonViewState>()
        Toast.makeText(getApplication(), "Se agrego un nuevo pokemon a favoritos", Toast.LENGTH_SHORT).show()
        state.pokeInfo.let {
            localRepository.upsert(it)
            updateViewState(
                state.copy(
                    isFav = true
                )
            )
        }
    }

    private fun getPokeInfo(id: Int) = viewModelScope.launch {
        val state = currentViewState<InfoPokemonViewState>()
        pokeRepository.getPokemonInfo(id).collect {
            when (it) {
                is NetworkResult.Error -> {
                    onError("Ocurrió un error al conseguir la información de la PokeApi")
                }

                is NetworkResult.Loading -> onLoadingChange(true)
                is NetworkResult.Success -> {
                    updateViewState(
                        state.copy(
                            pokeInfo = it.data ?: PokeInfoResponse(),
                            isFav = localRepository.getPokemonById(id) != null
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
        val state = currentViewState<InfoPokemonViewState>()
        updateViewState(
            state.copy(isLoading = it)
        )
    }
}