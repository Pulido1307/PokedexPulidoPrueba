package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.apps

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Aplicacion
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tec.crudbasededatos.domain.models.pivotes.Ver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAppsViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {
    private var dataBaseAplicacio: DatabaseReference = Firebase.database.getReference("Aplicacion")
    private var dataBaseVer: DatabaseReference = Firebase.database.getReference("Ver")

    init {
        initViewState(AddAppsViewState())
        getListApps()
    }

    fun onEvent(event: AddAppsViewEvent){
        when(event){
            is AddAppsViewEvent.AddApp -> addApp(event.id)
            is AddAppsViewEvent.OnChangeText -> onChangeText(event.text)
        }
    }

    private fun addApp(id: String) {
        val state = currentViewState<AddAppsViewState>()
        val idPush = dataBaseVer.push().key
        val app = Ver(
            idVer = idPush,
            clave = state.aplicaciones.find { it.nombre == state.app }?.clave?:"",
            codigo = id,
            nombre = state.app
        )

        dataBaseVer.child(idPush?:"").setValue(app).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nueva app agregada!", Toast.LENGTH_LONG).show()
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

    private fun onChangeText(text: String) {
        updateViewState(
            currentViewState<AddAppsViewState>().copy(
                app = text
            )
        )
    }

    private fun getListApps() {
        val listApps: ArrayList<Aplicacion> = arrayListOf()
        dataBaseAplicacio.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (app in snapshot.children) {
                        val appActual = app.getValue(Aplicacion::class.java)
                        listApps.add(appActual ?: Aplicacion())
                    }
                }
                updateViewState(
                    currentViewState<AddAppsViewState>().copy(
                        aplicaciones = listApps
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error $error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}