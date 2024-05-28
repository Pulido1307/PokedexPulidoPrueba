package com.antonio.pulido.pokedexpulido.ui.movies.movies.adds.resena

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Usuario
import com.antonio.pulido.pokedexpulido.viewmodel.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tec.crudbasededatos.domain.models.pivotes.Resenar
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddResenaViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {
    private var dataBaseUsuario: DatabaseReference = Firebase.database.getReference("Usuarios")
    private var dataBaseResenar: DatabaseReference = Firebase.database.getReference("Resenar")

    init {
        initViewState(AddResenaViewState())
        getUsuario()
    }

    fun onEvent(event: AddResenaViewEvent){
        when(event){
            is AddResenaViewEvent.AddResena -> addResena(event.id)
            is AddResenaViewEvent.OnChangeComentario -> onChangeComentario(event.comentario)
            is AddResenaViewEvent.OnChangeUsuario -> onChangeUsuario(event.usuario)
        }
    }

    private fun onChangeUsuario(usuario: String) {
        updateViewState(
            currentViewState<AddResenaViewState>().copy(
                usuario = usuario
            )
        )
    }

    private fun onChangeComentario(comentario: String) {
        updateViewState(
            currentViewState<AddResenaViewState>().copy(
                comentario = comentario
            )
        )
    }

    private fun addResena(id: String) {
        val state = currentViewState<AddResenaViewState>()
        val idPush = dataBaseResenar.push().key

        val resenar = Resenar(
            idResenar = idPush,
            clave = state.usuarios.find { it.nombre == state.usuario }?.clave?:"",
            mensaje = state.comentario,
            nombreUsuario = state.usuario,
            codigo = id
        )
        dataBaseResenar.child(idPush?:"").setValue(resenar).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nuevo reseña agregado!", Toast.LENGTH_LONG).show()
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

    private fun getUsuario() {
        val usuariosLista: ArrayList<Usuario> = arrayListOf()
        dataBaseUsuario.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (usuario in snapshot.children) {
                        val usuarioActual = usuario.getValue(Usuario::class.java)
                        usuariosLista.add(usuarioActual ?: Usuario())
                    }
                }

                updateViewState(
                    currentViewState<AddResenaViewState>().copy(
                        usuarios = usuariosLista
                    )
                )

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}