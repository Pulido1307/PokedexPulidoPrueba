package com.antonio.pulido.pokedexpulido.ui.movies.movies.list

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.antonio.pulido.pokedexpulido.domain.entidades.Aplicacion
import com.antonio.pulido.pokedexpulido.domain.entidades.Pelicula
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
class MoviesViewModel @Inject constructor(
    application: Application
): BaseViewModel(application){
    private var dataBase: DatabaseReference = Firebase.database.getReference("Peliculas")
    private var dataBaseApp: DatabaseReference = Firebase.database.getReference("Aplicacion")
    init {
        initViewState(MoviesViewState())
        getPeliculas()
    }


    private fun getPeliculas() {
        val peliculasLista: ArrayList<Pelicula> = arrayListOf()
        dataBase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               if(snapshot.exists()){
                   for (pelicula in snapshot.children){
                       val peliculaActual = pelicula.getValue(Pelicula::class.java)
                       peliculasLista.add(peliculaActual?:Pelicula())
                   }

                   updateViewState(
                       currentViewState<MoviesViewState>().copy(
                           peliculas = peliculasLista
                       )
                   )
               }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error ${error}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun onEvent(event: MoviesViewEvent){
        when(event){
            MoviesViewEvent.ShowDialogAddMovie -> setStatusAddDialog(true)
            MoviesViewEvent.HiddenDialogAddMovie -> setStatusAddDialog(false)
            MoviesViewEvent.AddMovie -> addMovie()
            is MoviesViewEvent.OnChangeDescripcion -> onChangeDescripcion(event.descripcion)
            is MoviesViewEvent.OnChangeDuracion -> onChangeDuracion(event.duracion)
            is MoviesViewEvent.OnChangeGenero -> onChangeGenero(event.genero)
            is MoviesViewEvent.OnChangeIdioma -> onChangeIdioma(event.idioma)
            is MoviesViewEvent.OnChangeName -> onChangeName(event.name)
            is MoviesViewEvent.OnChangeYear -> onChangeYear(event.year)
        }
    }

    private fun onChangeYear(year: String) {
        updateViewState(
            currentViewState<MoviesViewState>().copy(
                year = year,
                yearError = null
            )
        )
    }

    private fun onChangeName(name: String) {
        updateViewState(
            currentViewState<MoviesViewState>().copy(
                name = name,
                nameError = null
            )
        )
    }

    private fun onChangeIdioma(idioma: String) {
        updateViewState(
            currentViewState<MoviesViewState>().copy(
                idioma = idioma,
                idomaError = null
            )
        )
    }

    private fun onChangeGenero(genero: String) {
        updateViewState(
            currentViewState<MoviesViewState>().copy(
                genero = genero,
                generoError = null
            )
        )
    }

    private fun onChangeDuracion(duracion: String) {
        updateViewState(
            currentViewState<MoviesViewState>().copy(
                duracion = duracion,
                duracionError = null
            )
        )
    }

    private fun onChangeDescripcion(desc: String){
        updateViewState(
            currentViewState<MoviesViewState>().copy(
                descripcion = desc,
                descripcionError = null
            )
        )
    }

    private fun setStatusAddDialog(value: Boolean) {
        updateViewState(
            currentViewState<MoviesViewState>().copy(
                dialogAddMovie = value
            )
        )
    }
    private fun addApps() {
        val idPushAmazon = dataBaseApp.push().key
        val aplicacionAmazon = Aplicacion(
            clave = idPushAmazon,
            nombre = "Amazon Prime"
        )
        dataBaseApp.child(idPushAmazon?:"").setValue(aplicacionAmazon)

        val idPushDisneyPlus = dataBaseApp.push().key
        val aplicacionDisneyPlus = Aplicacion(
            clave = idPushDisneyPlus,
            nombre = "Disney Plus"
        )
        dataBaseApp.child(idPushDisneyPlus?:"").setValue(aplicacionDisneyPlus)

        val idPushNetflix = dataBaseApp.push().key
        val aplicacionNetflix = Aplicacion(
            clave = idPushNetflix,
            nombre = "Netflix"
        )
        dataBaseApp.child(idPushNetflix?:"").setValue(aplicacionNetflix)

        val idPushVix = dataBaseApp.push().key
        val aplicacionVix = Aplicacion(
            clave = idPushVix,
            nombre = "Vix"
        )
        dataBaseApp.child(idPushVix?:"").setValue(aplicacionVix)

        val idPushHBOMax = dataBaseApp.push().key
        val aplicacionHBOMax = Aplicacion(
            clave = idPushHBOMax,
            nombre = "HBO Max"
        )
        dataBaseApp.child(idPushHBOMax?:"").setValue(aplicacionHBOMax)

        val idPushParamount = dataBaseApp.push().key
        val aplicacionParamount = Aplicacion(
            clave = idPushParamount,
            nombre = "Paramount"
        )
        dataBaseApp.child(idPushParamount?:"").setValue(aplicacionParamount)

        val idPushStar = dataBaseApp.push().key
        val aplicacionStar = Aplicacion(
            clave = idPushStar,
            nombre = "Star+"
        )
        dataBaseApp.child(idPushStar?:"").setValue(aplicacionStar)
    }

    private fun addMovie() {
        val state = currentViewState<MoviesViewState>()


        val idPush = dataBase.push().key

        val pelicula = Pelicula(
            codigo = idPush,
            nombre = state.name,
            idioma = state.idioma,
            descripcion = state.descripcion,
            duracion = state.duracion.toInt(),
            year = state.year.toInt(),
            genero = state.genero
        )
        dataBase.child(idPush?:"").setValue(pelicula).addOnSuccessListener{
            Toast.makeText(getApplication(), "¡Nueva pelicula!", Toast.LENGTH_LONG).show()
            updateViewState(
                state.copy(
                    dialogAddMovie = false
                )
            )
            getPeliculas()
        }.addOnFailureListener {
            Toast.makeText(getApplication(), "¡Fallo la pelicula!", Toast.LENGTH_LONG).show()
            Log.i("Polar dice error", "prueba: ${it.message}")
        }
    }


}