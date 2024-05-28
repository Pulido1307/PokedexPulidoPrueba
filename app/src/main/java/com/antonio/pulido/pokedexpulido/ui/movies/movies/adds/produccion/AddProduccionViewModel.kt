package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.produccion

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Productora
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tec.crudbasededatos.domain.models.pivotes.Producir
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddProduccionViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application){
    private var dataBaseProducir: DatabaseReference = Firebase.database.getReference("Producir")
    private var dataBaseProductores: DatabaseReference =
        Firebase.database.getReference("Productora")
    init {
        initViewState(AddProduccionViewState())
        getProduccion()
    }
    fun onEvent(event: AddProduccionViewEvent){
        when(event){
            is AddProduccionViewEvent.AddProduccion -> addProduccion(event.id)
            is AddProduccionViewEvent.OnChangeText -> onChangeText(event.text)
        }
    }

    private fun addProduccion(id: String) {
        val state = currentViewState<AddProduccionViewState>()
        val idPush = dataBaseProducir.push().key
        val producir = Producir(
            idProduccion = idPush,
            clave = state.producciones.find { it.nombre == state.produccion }?.clave?:"",
            codigo = id,
            nombre = state.produccion
        )

        dataBaseProducir.child(idPush?:"").setValue(producir).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nueva producción agregada!", Toast.LENGTH_LONG).show()
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

    private fun getProduccion() {
        val productoresLista: ArrayList<Productora> = arrayListOf()
        dataBaseProductores.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (productor in snapshot.children) {
                        val productoraActual = productor.getValue(Productora::class.java)
                        productoresLista.add(productoraActual ?: Productora())
                    }
                }

                updateViewState(
                    currentViewState<AddProduccionViewState>().copy(
                        producciones = productoresLista
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()

            }

        })
    }
    private fun onChangeText(text: String) {
        updateViewState(
            currentViewState<AddProduccionViewState>().copy(
                produccion = text
            )
        )
    }
}