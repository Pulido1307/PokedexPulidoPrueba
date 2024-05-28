package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.direcciones

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Director
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tec.crudbasededatos.domain.models.pivotes.Dirigir
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDIreccionesViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application){
    private var dataBaseDirector: DatabaseReference = Firebase.database.getReference("Directores")
    private var dataBaseDirigir: DatabaseReference = Firebase.database.getReference("Dirigir")
    init {
        initViewState(AddDIreccionesViewState())
        getDirectores()
    }

    fun onEvent(event: AddDIreccionesViewEvent){
        when(event){
            is AddDIreccionesViewEvent.AddDireccion -> addDireccion(event.id)
            is AddDIreccionesViewEvent.OnChangeDirector -> onChangeDirector(event.director)
        }
    }

    private fun onChangeDirector(director: String) {
        val state = currentViewState<AddDIreccionesViewState>()
        updateViewState(
            state.copy(
                director = director
            )
        )
    }

    private fun addDireccion(id: String) {
        val state = currentViewState<AddDIreccionesViewState>()
        val idPush = dataBaseDirigir.push().key

        val dirigir = Dirigir(
            noDirigida = idPush,
            codigo = id,
            id = state.directores.find { it.nombre == state.director }?.id ?: ""
        )

        updateViewState(
            state.copy(isLoading = true)
        )

        if (idPush != null) {
            dataBaseDirigir.child(idPush).setValue(dirigir).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(getApplication(), "¡Nuevo Director agregado!", Toast.LENGTH_LONG).show()
                    updateViewState(
                        state.copy(successAdd = true, isLoading = false)
                    )
                } else {
                    Toast.makeText(getApplication(), "¡Fallo la pelicula!", Toast.LENGTH_LONG).show()
                    Log.e("AddDireccion", "Error: ${task.exception?.message}")
                    updateViewState(
                        state.copy(isLoading = false)
                    )
                }
            }
        } else {
            Toast.makeText(getApplication(), "¡Error al generar ID!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(isLoading = false)
            )
        }
    }


    private fun getDirectores() {
        val directoresLista: ArrayList<Director> = arrayListOf()
        dataBaseDirector.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (director in snapshot.children) {
                        val directorActual = director.getValue(Director::class.java)
                        directoresLista.add(directorActual ?: Director())
                    }
                }

                updateViewState(
                    currentViewState<AddDIreccionesViewState>().copy(
                        directores = directoresLista
                    )
                )

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error $error", Toast.LENGTH_SHORT).show()

            }
        })
    }
}