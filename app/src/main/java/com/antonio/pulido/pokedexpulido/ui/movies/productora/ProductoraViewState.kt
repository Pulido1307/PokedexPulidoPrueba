package com.antonio.pulido.pokedexpulido.ui.movies.productora

import androidx.annotation.StringRes
import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Productora
import com.antonio.pulido.pokedexpulido.viewstate.ViewState

data class ProductoraViewState(
    val isloanding: Boolean = false,

    val showAddProductora: Boolean = false,

    val name: String = "",
    @StringRes val nameError: Int? = null,

    val pais:String="",
    @StringRes val paisError: Int?=null,

    val productora: List<Productora> = listOf(),
): ViewState()

