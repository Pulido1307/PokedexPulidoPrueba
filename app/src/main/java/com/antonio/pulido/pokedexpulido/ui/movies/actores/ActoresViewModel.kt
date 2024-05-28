package com.antonio.pulido.pokedexpulido.ui.movies.actores

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class ActoresViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Actores")

    init {
        initViewState(ActoresViewState())
        getActores()
    }

    private fun getActores() {
        val actoresLista: ArrayList<Actor> = arrayListOf()
        dataBase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (actor in snapshot.children){
                        val actorActual = actor.getValue(Actor::class.java)
                        actoresLista.add(actorActual?: Actor())
                    }
                }

                updateViewState(
                    currentViewState<ActoresViewState>().copy(
                        actores = actoresLista
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error $error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onEvent(event: ActoresViewEvent){
        when(event){
            ActoresViewEvent.AddActor -> addActor()
            ActoresViewEvent.ShowDialogAddActor -> setStatusAddDialog(true)
            ActoresViewEvent.HiddenDialogAddActor -> setStatusAddDialog(false)
            is ActoresViewEvent.OnChangeEdad -> onChangeEdad(event.edad)
            is ActoresViewEvent.OnChangeNacionalidad -> onChangeNacionalidad(event.nacionalidad)
            is ActoresViewEvent.OnChangeName -> onChangeName(event.name)
            is ActoresViewEvent.ShowDialogInfoActor -> showDialogInfoActor(event.item)
            ActoresViewEvent.HiddenDialogInfoActor -> setInfoActor(false)
        }
    }

    private fun showDialogInfoActor(item: Actor) {
        updateViewState(
            currentViewState<ActoresViewState>().copy(
                actorSeleccionado = item
            )
        )
    }

    private fun setInfoActor(value: Boolean) {
        updateViewState(
            currentViewState<ActoresViewState>().copy(
                showInfoActor = value
            )
        )
    }

    private fun addActor() {
        val idPush = dataBase.push().key
        val state = currentViewState<ActoresViewState>()

        val actor = Actor(
            dni = idPush,
            nombre = state.name,
            nacionalidad = state.nacionalidad,
            edad = state.edad.toInt()
        )

        dataBase.child(idPush?:"").setValue(actor).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nueva actor!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    showAddActor = false
                )
            )
            getActores()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Error al agregar un Actor!", Toast.LENGTH_LONG).show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
    }

    private fun onChangeName(name: String) {
        updateViewState(
            currentViewState<ActoresViewState>().copy(
                name = name
            )
        )
    }


    private fun onChangeNacionalidad(nacionalidad: String) {
        updateViewState(
            currentViewState<ActoresViewState>().copy(
                nacionalidad = nacionalidad
            )
        )
    }

    private fun onChangeEdad(edad: String) {
        updateViewState(
            currentViewState<ActoresViewState>().copy(
                edad = edad
            )
        )
    }

    private fun setStatusAddDialog(value: Boolean) {
        updateViewState(
            currentViewState<ActoresViewState>().copy(
                showAddActor = value
            )
        )
    }
}