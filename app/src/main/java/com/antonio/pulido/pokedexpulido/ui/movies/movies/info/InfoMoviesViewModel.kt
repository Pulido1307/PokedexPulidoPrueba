package com.antonio.pulido.pokedexpulido.ui.movies.movies.info

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Director
import com.antonio.pulido.pokedexpulido.domain.entidades.Pelicula
import com.antonio.pulido.pokedexpulido.domain.entidades.Productora
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
class InfoMoviesViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Peliculas")
    private var dataBaseActores: DatabaseReference = Firebase.database.getReference("Actores")
    private var dataBaseDirector: DatabaseReference = Firebase.database.getReference("Director")
    private var dataBaseProductores: DatabaseReference =
        Firebase.database.getReference("Productores")
    private var dataBaseActuar: DatabaseReference = Firebase.database.getReference("Actuar")
    private var dataBaseDirigir: DatabaseReference = Firebase.database.getReference("Dirigir")
    private var dataBaseProducir: DatabaseReference = Firebase.database.getReference("Producir")
    private var dataBaseResenar: DatabaseReference = Firebase.database.getReference("Resenar")

    init {
        initViewState(InfoMoviesViewState())
    }

    fun onEvent(event: InfoMoviesViewEvent) {
        when (event) {
            InfoMoviesViewEvent.DeleteInfoMovie -> deleteMovie()
            is InfoMoviesViewEvent.GetInfo -> getInfo(event.id)
            is InfoMoviesViewEvent.OnChangeActorText -> onChangeActorText(event.text)
            InfoMoviesViewEvent.HiddenEditActor -> setEditActor(false)
            InfoMoviesViewEvent.ShowEditActor -> setEditActor(true)
            InfoMoviesViewEvent.EditActor -> editActor()
        }
    }

    private fun editActor() {
        val state = currentViewState<InfoMoviesViewState>()
        val idPush = dataBase.push().key

        val actuar = Actuar(
            idActuar = idPush,
            idActor = state.actores.find { it.nombre == state.actorSeleccionado }?.dni?:"",
            codigo = state.id
        )

        dataBaseActuar.child(idPush ?:"").setValue(actuar).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nuevo Actor agregado!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    showEditActor = false
                )
            )
            getActuaciones()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Fallo la pelicula!", Toast.LENGTH_LONG).show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
    }

    private fun setEditActor(value: Boolean) {
        updateViewState(
            currentViewState<InfoMoviesViewState>().copy(
                showEditActor = value
            )
        )
    }

    private fun onChangeActorText(text: String) {
        updateViewState(
            currentViewState<InfoMoviesViewState>().copy(
                actorSeleccionado = text
            )
        )
    }


    private fun deleteMovie() {
        val state = currentViewState<InfoMoviesViewState>()
        dataBase.child(state.id).removeValue().addOnSuccessListener {
            Toast.makeText(getApplication(), "Película borrada", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "Error ${it.message}", Toast.LENGTH_SHORT).show()
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
                        isLoading = false,
                        id = id
                    )
                )
            }

            getDirectores()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "Error ${it.message}", Toast.LENGTH_SHORT).show()
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
                    currentViewState<InfoMoviesViewState>().copy(
                        directores = directoresLista
                    )
                )

                getActores()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()

            }

        })
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
                    currentViewState<InfoMoviesViewState>().copy(
                        actores = actorLista
                    )
                )
                getProductores()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun getProductores() {
        val productoresLista: ArrayList<Productora> = arrayListOf()
        dataBaseProductores.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (productor in snapshot.children) {
                        val productoraActual = productor.getValue(Productora::class.java)
                        productoresLista.add(productoraActual ?: Productora())
                    }
                }
                getActuaciones()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun getActuaciones() {
        val state = currentViewState<InfoMoviesViewState>()
        val actores = state.actores
        val actuaciones: ArrayList<Actuar> = arrayListOf()
        val actoresInvolucrados: ArrayList<Actor> = arrayListOf()
        dataBaseActuar.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (actuacion in snapshot.children) {
                        val actuacionActual = actuacion.getValue(Actuar::class.java)
                        if (state.id == actuacionActual?.codigo) {
                            actuaciones.add(actuacionActual ?: Actuar())
                        }
                    }
                }


                for (actor in actores) {
                    if (actuaciones.any { it.idActor == actor.dni }) {
                        actoresInvolucrados.add(actor)
                    }
                }

                updateViewState(
                    state.copy(
                        actoresInvolucrados = actoresInvolucrados
                    )
                )

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}