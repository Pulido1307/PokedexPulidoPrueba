package com.antonio.pulido.pokedexpulido.ui.movies.productora

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Productora
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewEvent
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewState
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProductoraViewModel(
    application: Application
) : BaseViewModel(application) {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Productora")

    init {
        initViewState(ActoresViewState())
        getProductora()
    }

    private fun getProductora() {
        val productoraLista: ArrayList<Productora> = arrayListOf()
        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (productora in snapshot.children) {
                        val productoraActual = productora.getValue(Productora::class.java)
                        productoraLista.add(productoraActual ?: Productora())
                    }
                }

                updateViewState(
                    currentViewState<ProductoraViewState>().copy(
                        productora = productoraLista
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onEvent(event: ProductoraViewEvent) {
        when (event) {
            ProductoraViewEvent.AddProductora -> addProductora()
            ProductoraViewEvent.ShowDialogAddProductora -> setStatusAddDialog(true)
            ProductoraViewEvent.HiddenDialogAddProductora -> setStatusAddDialog(false)
            is ProductoraViewEvent.OnChangePais -> onChangePais(event.pais)
            is ProductoraViewEvent.OnChangeName -> onChangeName(event.name)
            is ProductoraViewEvent.ShowDialogInfoProductora -> showDialogInfoProductora(event.item)
            ProductoraViewEvent.HiddenDialogInfoProductora -> setInfoProductora(false)
        }
    }

    private fun showDialogInfoProductora(item: Productora) {
        updateViewState(
            currentViewState<ProductoraViewState>().copy(
                productoraSeleccionada = item
            )
        )
    }

    private fun setInfoProductora(value: Boolean) {
        updateViewState(
            currentViewState<ProductoraViewState>().copy(
                showInfoProductora = value
            )
        )
    }

    private fun setStatusAddDialog(b: Boolean) {
        updateViewState(
            currentViewState<ProductoraViewState>().copy(
                showAddProductora = b
            )
        )
    }

    private fun addProductora() {
        val idPush = dataBase.push().key
        val state = currentViewState<ProductoraViewState>()

        val productora = Productora(
            clave = idPush,
            nombre = state.name,
            pais = state.pais,
        )

        dataBase.child(idPush ?: "").setValue(productora).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nueva productora!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    showAddProductora = false
                )
            )
            getProductora()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Error al agregar una productora!", Toast.LENGTH_LONG)
                .show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
    }

    private fun onChangeName(name: String) {
        updateViewState(
            currentViewState<ProductoraViewState>().copy(
                name = name
            )
        )
    }


    private fun onChangePais(pais: String) {
        updateViewState(
            currentViewState<ProductoraViewState>().copy(
                pais = pais
            )
        )
    }
}

