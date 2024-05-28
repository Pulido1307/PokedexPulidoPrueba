package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.actuacion

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.pivotes.Actuar
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddActuacionViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application){
    private var dataBaseActuar: DatabaseReference = Firebase.database.getReference("Actuar")
    private var dataBaseActores: DatabaseReference = Firebase.database.getReference("Actores")

    init {
        initViewState(AddActuacionViewState())
        getActores()
    }
    fun onEvent(event: AddActuacionViewEvent){
        when(event){
            is AddActuacionViewEvent.AddActuacion -> addActuacion(event.id)
            is AddActuacionViewEvent.OnChangeActor -> onChangeActor(event.actor)
        }
    }

    private fun onChangeActor(actor: String) {
        val state = currentViewState<AddActuacionViewState>()
        updateViewState(
            state.copy(
                actor = actor
            )
        )
    }

    private fun getActores() {
        val actorLista: ArrayList<Actor> = arrayListOf()
        dataBaseActores.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (actor in snapshot.children) {
                        val actorActual = actor.getValue(Actor::class.java)
                        actorLista.add(actorActual ?: Actor())
                    }
                }

                updateViewState(
                    currentViewState<AddActuacionViewState>().copy(
                        actores = actorLista
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error $error", Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun addActuacion(id: String) {
        val state = currentViewState<AddActuacionViewState>()
        val idPush = dataBaseActuar.push().key

        val actuar = Actuar(
            idActuar = idPush,
            idActor = state.actores.find { it.nombre == state.actor }?.dni?:"",
            codigo = id
        )
        dataBaseActuar.child(idPush?:"").setValue(actuar).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nuevo Actor agregado!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    successAdd = true
                )
            )

        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Fallo la pelicula!", Toast.LENGTH_LONG).show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
    }
}