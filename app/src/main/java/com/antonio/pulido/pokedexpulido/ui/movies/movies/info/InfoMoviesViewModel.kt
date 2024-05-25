package com.antonio.pulido.pokedexpulido.ui.movies.movies.info

import android.app.Application
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Pelicula
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoMoviesViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Peliculas")

    init {
        initViewState(InfoMoviesViewState())
    }

    fun onEvent(event: InfoMoviesViewEvent) {
        when (event) {
            is InfoMoviesViewEvent.GetInfo -> getInfo(event.id)
        }
    }

    private fun getInfo(id: String) {
        updateViewState(
            currentViewState<InfoMoviesViewState>().copy(
                isLoading = true
            )
        )
        dataBase.child(id).get().addOnSuccessListener {
            if (it.exists()) {
                updateViewState(
                    currentViewState<InfoMoviesViewState>().copy(
                        pelicula = it.getValue(Pelicula::class.java) ?: Pelicula(),
                        isLoading = false
                    )
                )
            }
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "Error ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }
}