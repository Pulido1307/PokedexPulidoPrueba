package com.antonio.pulido.pokedexpulido.ui.movies.movies.update

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Pelicula
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
            is UpdateMovieViewEvent.GetInfo -> getInfo(event.id)
            UpdateMovieViewEvent.UpdateMovie -> updateMovie()
        }
    }

    private fun getInfo(id: String) {
        updateViewState(
            currentViewState<UpdateMovieViewState>().copy(
                isLoading = true
            )
        )
        dataBase.child(id).get().addOnSuccessListener {
            if (it.exists()) {
                val info = it.getValue(Pelicula::class.java) ?: Pelicula()
                updateViewState(
                    currentViewState<UpdateMovieViewState>().copy(
                        isLoading = false,
                        codigo = info.codigo?:"",
                        name = info.nombre?:"",
                        idioma = info.idioma?:"",
                        descripcion = info.descripcion?:"",
                        duracion = info.duracion.toString(),
                        year = info.year.toString(),
                        genero = info.genero?:""
                    )
                )
            }
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "Error ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateMovie() {
        val state = currentViewState<UpdateMovieViewState>()
        val childUpdate = hashMapOf<String, Any>(
            "codigo" to state.codigo,
            "nombre" to state.name,
            "idioma" to state.idioma,
            "descripcion" to state.descripcion,
            "duracion" to state.duracion,
            "year" to state.year,
            "genero" to state.genero
        )

        dataBase.child(state.codigo).updateChildren(childUpdate).addOnCompleteListener {
            Toast.makeText(getApplication(), "¡Pelicula actualizada!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    successUpdate = true
                )
            )
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Fallo la pelicula!", Toast.LENGTH_LONG).show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
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