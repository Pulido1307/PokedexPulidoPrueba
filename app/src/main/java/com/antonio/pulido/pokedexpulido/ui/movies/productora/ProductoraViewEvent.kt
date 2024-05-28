package com.antonio.pulido.pokedexpulido.ui.movies.productora

import com.antonio.pulido.pokedexpulido.domain.entidades.Actor
import com.antonio.pulido.pokedexpulido.domain.entidades.Productora
import com.antonio.pulido.pokedexpulido.ui.movies.actores.ActoresViewEvent


sealed interface ProductoraViewEvent {
    object ShowDialogAddProductora: ProductoraViewEvent
    object HiddenDialogAddProductora: ProductoraViewEvent
    data class ShowDialogInfoProductora(val item: Productora): ProductoraViewEvent
    object HiddenDialogInfoProductora: ProductoraViewEvent
    object AddProductora : ProductoraViewEvent
    data class OnChangeName(val name: String): ProductoraViewEvent
    data class OnChangePais(val pais: String): ProductoraViewEvent

}