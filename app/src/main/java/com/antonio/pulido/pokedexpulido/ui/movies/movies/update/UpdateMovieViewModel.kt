package com.antonio.pulido.pokedexpulido.ui.movies.movies.update

import android.app.Application
import com.antonio.pulido.pokedexpulido.domain.entidades.Pelicula
import com.antonio.pulido.pokedexpulido.ui.movies.movies.list.MoviesViewState
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateMovieViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Peliculas")

    init {
        initViewState(UpdateMovieViewState())
    }
    fun onEvent(event: UpdateMovieViewEvent){
        when(event){
            is UpdateMovieViewEvent.OnChangeDescripcion -> onChangeDescripcion(event.descripcion)
            is UpdateMovieViewEvent.OnChangeDuracion -> onChangeDuracion(event.duracion)
            is UpdateMovieViewEvent.OnChangeGenero -> onChangeGenero(event.genero)
            is UpdateMovieViewEvent.OnChangeIdioma -> onChangeIdioma(event.idioma)
            is UpdateMovieViewEvent.OnChangeName -> onChangeName(event.name)
            is UpdateMovieViewEvent.OnChangeYear -> onChangeYear(event.year)
            UpdateMovieViewEvent.UpdateMovie -> updateMovie()
        }
    }

    private fun updateMovie() {
        val state = currentViewState<UpdateMovieViewState>()
        val updteMovie = Pelicula(

        )
    }

    private fun onChangeYear(year: String) {
        updateViewState(
            currentViewState<UpdateMovieViewState>().copy(
                year = year,
                yearError = null
            )
        )
    }

    private fun onChangeName(name: String) {
        updateViewState(
            currentViewState<UpdateMovieViewState>().copy(
                name = name,
                nameError = null
            )
        )
    }

    private fun onChangeIdioma(idioma: String) {
        updateViewState(
            currentViewState<UpdateMovieViewState>().copy(
                idioma = idioma,
                idomaError = null
            )
        )
    }

    private fun onChangeGenero(genero: String) {
        updateViewState(
            currentViewState<UpdateMovieViewState>().copy(
                genero = genero,
                generoError = null
            )
        )
    }

    private fun onChangeDuracion(duracion: String) {
        updateViewState(
            currentViewState<UpdateMovieViewState>().copy(
                duracion = duracion,
                duracionError = null
            )
        )
    }

    private fun onChangeDescripcion(descripcion: String) {
        updateViewState(
            currentViewState<UpdateMovieViewState>().copy(
                descripcion = descripcion,
                descripcionError = null
            )
        )
    }
}