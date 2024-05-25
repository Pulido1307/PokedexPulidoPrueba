package com.antonio.pulido.pokedexpulido.ui.movies.usuarios

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Aplicacion
import com.antonio.pulido.pokedexpulido.domain.entidades.Usuario
import com.antonio.pulido.pokedexpulido.ui.movies.usuarios.UsuariosViewState
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
class UsuariosViewModel @Inject constructor(
    aplicacion: Application
) : BaseViewModel(aplicacion) {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Usuarios")

    init {
        initViewState(UsuariosViewState())
        getUsuarios()
    }

    fun onEvent(event: UsuariosViewEvent){
        when(event){
            UsuariosViewEvent.HiddenDialogAddUsuario -> setStatusAddDialog(false)
            UsuariosViewEvent.ShowDialogAddUsuario -> setStatusAddDialog(true)
            UsuariosViewEvent.addUsuario -> addUsuario()
            is UsuariosViewEvent.onChangeGenero -> onChangeGenero(event.genero)
            is UsuariosViewEvent.onChangeNombre -> onChangeName(event.nombre)
            is UsuariosViewEvent.onChangePais -> onChangePais(event.pais)
        }
    }
    private fun getUsuarios() {
        val usuariosLista: ArrayList<Usuario> = arrayListOf()
        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (usuario in snapshot.children) {
                        val usuarioActual = usuario.getValue(Usuario::class.java)
                        usuariosLista.add(usuarioActual ?: Usuario())
                    }
                }

                updateViewState(
                    currentViewState<UsuariosViewState>().copy(
                        usuarios = usuariosLista
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addUsuario() {
        val idPush = dataBase.push().key
        val state = currentViewState<UsuariosViewState>()

        val usuario = Usuario(
            clave = idPush,
            nombre = state.name,
            pais = state.pais,
            genero = state.genero,
        )

        dataBase.child(idPush?:"").setValue(usuario).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nuevo usuario!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    showAddAUsuario = false
                )
            )
            getUsuarios()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Error al agregar un Usuario!", Toast.LENGTH_LONG).show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
    }

    private fun onChangeName(name: String)
    {
        updateViewState(
            currentViewState<UsuariosViewState>().copy(
                name = name
            )
        )
    }

    private fun onChangePais(pais: String) {
        updateViewState(
            currentViewState<UsuariosViewState>().copy(
                pais = pais
            )
        )
    }

    private fun onChangeGenero(genero: String) {
        updateViewState(
            currentViewState<UsuariosViewState>().copy(
                genero = genero
            )
        )
    }

    private fun setStatusAddDialog(value: Boolean) {
        updateViewState(
            currentViewState<UsuariosViewState>().copy(
                showAddAUsuario = value
            )
        )
    }
}