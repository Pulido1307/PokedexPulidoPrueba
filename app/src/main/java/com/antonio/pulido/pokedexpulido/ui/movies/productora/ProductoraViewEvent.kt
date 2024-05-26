package com.antonio.pulido.pokedexpulido.ui.movies.productora


sealed interface ProductoraViewEvent {
    object ShowDialogAddProductora: ProductoraViewEvent
    object HiddenDialogAddProductora: ProductoraViewEvent
    object AddProductora : ProductoraViewEvent
    data class OnChangeName(val name: String): ProductoraViewEvent
    data class OnChangePais(val pais: String): ProductoraViewEvent

}