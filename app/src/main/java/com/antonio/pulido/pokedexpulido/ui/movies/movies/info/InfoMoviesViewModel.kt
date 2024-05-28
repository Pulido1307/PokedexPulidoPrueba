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
import com.tec.crudbasededatos.domain.models.pivotes.Dirigir
import com.tec.crudbasededatos.domain.models.pivotes.Producir
import com.tec.crudbasededatos.domain.models.pivotes.Resenar
import com.tec.crudbasededatos.domain.models.pivotes.Ver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoMoviesViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {
    private var dataBase: DatabaseReference = Firebase.database.getReference("Peliculas")
    private var dataBaseActores: DatabaseReference = Firebase.database.getReference("Actores")
    private var dataBaseDirector: DatabaseReference = Firebase.database.getReference("Directores")
    private var dataBaseProductores: DatabaseReference =
        Firebase.database.getReference("Productores")
    private var dataBaseActuar: DatabaseReference = Firebase.database.getReference("Actuar")
    private var dataBaseDirigir: DatabaseReference = Firebase.database.getReference("Dirigir")
    private var dataBaseProducir: DatabaseReference = Firebase.database.getReference("Producir")
    private var dataBaseResenar: DatabaseReference = Firebase.database.getReference("Resenar")
    private var dataBaseVer: DatabaseReference = Firebase.database.getReference("Ver")


    init {
        initViewState(InfoMoviesViewState())
    }

    fun onEvent(event: InfoMoviesViewEvent) {
        when (event) {
            InfoMoviesViewEvent.DeleteInfoMovie -> deleteMovie()
            is InfoMoviesViewEvent.GetInfo -> getInfo(event.id)
            is InfoMoviesViewEvent.OnChangeActorText -> onChangeActorText(event.text)
            is InfoMoviesViewEvent.OnChangeDirectorText -> onChangeDirectorText(event.text)
            is InfoMoviesViewEvent.OnChangeProductorText -> onChangeProductorText(event.text)
            InfoMoviesViewEvent.HiddenEditActor -> setEditActor(false)
            InfoMoviesViewEvent.ShowEditActor -> setEditActor(true)
            InfoMoviesViewEvent.HiddenEditDirector -> setEditDirector(false)
            InfoMoviesViewEvent.ShowEditDirector -> setEditDirector(true)
            InfoMoviesViewEvent.EditActor -> editActor()
            InfoMoviesViewEvent.EditDirector -> editDirector()
            InfoMoviesViewEvent.EditProductor -> editProductor()
            InfoMoviesViewEvent.HiddenEditPrdoctor -> setStatusEditProductor(false)
            InfoMoviesViewEvent.ShowEditdProductor -> setStatusEditProductor(true)
        }
    }

    private fun setStatusEditProductor(value: Boolean) {
        updateViewState(
            currentViewState<InfoMoviesViewState>().copy(
                showEditProductora = value
            )
        )
    }


    private fun setEditDirector(value: Boolean) {
        updateViewState(
            currentViewState<InfoMoviesViewState>().copy(
                showEditDirector = value
            )
        )
    }

    private fun editProductor() {
        val state = currentViewState<InfoMoviesViewState>()
        val idPush = dataBaseProducir.push().key
        val producir = Producir(
            idProduccion = idPush,
            codigo = state.id,
            clave = state.produtores.find { it.nombre == state.productorSeleccionado }?.clave ?: ""
        )
        dataBaseProducir.child(idPush ?: "").setValue(producir).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nuevo Productor agregado!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    showEditProductora = false
                )
            )
            getDirecciones()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Fallo la pelicula!", Toast.LENGTH_LONG).show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
    }

    private fun editDirector() {
        val state = currentViewState<InfoMoviesViewState>()
        val idPush = dataBaseDirigir.push().key
        val dirigir = Dirigir(
            noDirigida = idPush,
            codigo = state.id,
            id = state.directores.find { it.nombre == state.directorSeleccionado }?.id ?: ""
        )

        dataBaseDirigir.child(idPush ?: "").setValue(dirigir).addOnSuccessListener {
            Toast.makeText(getApplication(), "¡Nuevo Directo agregado!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    showEditDirector = false
                )
            )
            getDirecciones()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Fallo la pelicula!", Toast.LENGTH_LONG).show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
    }

    private fun editActor() {
        val state = currentViewState<InfoMoviesViewState>()
        val idPush = dataBaseActuar.push().key

        val actuar = Actuar(
            idActuar = idPush,
            idActor = state.actores.find { it.nombre == state.actorSeleccionado }?.dni ?: "",
            codigo = state.id
        )

        dataBaseActuar.child(idPush ?: "").setValue(actuar).addOnSuccessListener {
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

    private fun onChangeProductorText(text: String) {
        updateViewState(
            currentViewState<InfoMoviesViewState>().copy(
                productorSeleccionado = text
            )
        )
    }

    private fun onChangeDirectorText(text: String) {
        updateViewState(
            currentViewState<InfoMoviesViewState>().copy(
                directorSeleccionado = text
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
            updateViewState(
                state.copy(
                    successDelete = true
                )
            )
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
                getDirecciones()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getDirecciones() {
        val state = currentViewState<InfoMoviesViewState>()
        val directores = state.directores
        val direcciones: ArrayList<Dirigir> = arrayListOf()
        val directoresInvolucrados: ArrayList<Director> = arrayListOf()
        dataBaseDirigir.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (direccion in snapshot.children) {
                        val direccionActual = direccion.getValue(Dirigir::class.java)
                        if (state.id == direccionActual?.codigo) {
                            direcciones.add(direccionActual ?: Dirigir())
                        }
                    }
                }

                for (director in directores) {
                    if (direcciones.any { it.id == director.id }) {
                        directoresInvolucrados.add(director)
                    }
                }

                updateViewState(
                    state.copy(
                        directoresInvolucrados = directoresInvolucrados
                    )
                )
                Log.i("TAG", "onDataChange: ${direcciones.toString()}")
                getResenas()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error $error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getResenas() {
        val state = currentViewState<InfoMoviesViewState>()
        val resenas: ArrayList<Resenar> = arrayListOf()
        dataBaseResenar.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (resena in snapshot.children) {
                        val resenaActual = resena.getValue(Resenar::class.java)
                        if (resenaActual != null) {
                            resenas.add(resenaActual)
                            Log.d("FirebaseData", "Resena obtenida: $resenaActual")
                        } else {
                            Log.d("FirebaseData", "Resena es null")
                        }
                    }
                } else {
                    Log.d("FirebaseData", "Snapshot no existe")
                }

                resenas.removeIf { it.codigo!=state.id }
                updateViewState(
                    state.copy(
                        resenas = resenas
                    )
                )
                getAplicacionesList()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error $error", Toast.LENGTH_SHORT).show()
                Log.e("FirebaseError", "Error al obtener las reseñas: $error")
            }
        })
    }
    private fun getAplicacionesList(){
        val state = currentViewState<InfoMoviesViewState>()
        val ver: ArrayList<Ver> = arrayListOf()
        dataBaseVer.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for(vista in snapshot.children){
                        val verActual = vista.getValue(Ver::class.java)
                        ver.add(verActual?:Ver())
                    }
                }
                ver.removeIf { it.codigo!=state.id }

                updateViewState(
                    currentViewState<InfoMoviesViewState>().copy(
                        aplicaciones = ver
                    )
                )
                getProduccionesIn()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error $error", Toast.LENGTH_SHORT).show()
                Log.e("FirebaseError", "Error al obtener las reseñas: $error")
            }

        })
    }
    private fun getProduccionesIn(){
        val state = currentViewState<InfoMoviesViewState>()
        val producir: ArrayList<Producir> = arrayListOf()
        dataBaseProducir.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for(prod in snapshot.children){
                        val prodActual = prod.getValue(Producir::class.java)
                        producir.add(prodActual?:Producir())
                    }
                }
                producir.removeIf { it.codigo!=state.id }
                updateViewState(
                    currentViewState<InfoMoviesViewState>().copy(
                        producir = producir
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error $error", Toast.LENGTH_SHORT).show()
                Log.e("FirebaseError", "Error al obtener las reseñas: $error")
            }

        })
    }
    private fun getProducidas() {
        val state = currentViewState<InfoMoviesViewState>()
        val produtores = state.produtores
        val producciones: ArrayList<Producir> = arrayListOf()
        val produccionesInvolucradas: ArrayList<Productora> = arrayListOf()
        dataBaseProducir.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (produccion in snapshot.children) {
                        val produccionActual = produccion.getValue(Producir::class.java)
                        if (state.id == produccionActual?.codigo) {
                            producciones.add(produccionActual ?: Producir())
                        }
                    }
                    for (productor in produtores) {
                        if (producciones.any { it.clave == productor.clave }) {
                            produccionesInvolucradas.add(productor)
                        }
                    }

                    updateViewState(
                        state.copy(
                            produccionesInvolucradas = produccionesInvolucradas
                        )
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error $error", Toast.LENGTH_SHORT).show()
            }

        })
    }

}