package com.antonio.pulido.pokedexpulido.ui.movies.director

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Director
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewEvent
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewState
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class DirectorViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Directores")

    init {
        initViewState(ActoresViewState())
        getDirectores()
    }

    private fun getDirectores() {
        val directoresLista: ArrayList<Director> = arrayListOf()
        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (director in snapshot.children) {
                        val directorActual = director.getValue(Director::class.java)
                        directoresLista.add(directorActual ?: Director())
                    }
                }

                updateViewState(
                    currentViewState<DirectorViewState>().copy(
                        directores = directoresLista
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onEvent(event: DirectorViewEvent) {
        when (event) {
            DirectorViewEvent.AddDirector -> addDirector()
            DirectorViewEvent.ShowDialogAddDirector -> setStatusAddDialog(true)
            DirectorViewEvent.HiddenDialogAddDirector -> setStatusAddDialog(false)
            is DirectorViewEvent.OnChangeEdad -> onChangeEdad(event.edad)
            is DirectorViewEvent.OnChangeName -> onChangeName(event.name)
        }
    }

    private fun addDirector() {
        val idPush = dataBase.push().key
        val state = currentViewState<DirectorViewState>()

        val actor = Director(
            id = idPush,
            nombre = state.name,
            edad = state.edad.toInt()
        )

        dataBase.child(idPush ?: "").setValue(director).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nuevo director!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    showAddDirector = false
                )
            )
            getDirectores()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Error al agregar un director!", Toast.LENGTH_LONG)
                .show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
    }

    private fun onChangeName(name: String) {
        updateViewState(
            currentViewState<DirectorViewState>().copy(
                name = name
            )
        )
    }

    private fun onChangeEdad(edad: String) {
        updateViewState(
            currentViewState<DirectorViewState>().copy(
                edad = edad
            )
        )
    }

    private fun setStatusAddDialog(value: Boolean) {
        updateViewState(
            currentViewState<DirectorViewState>().copy(
                showAddDirector = value
            )
        )
    }
}