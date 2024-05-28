package com.antonio.pulido.pokedexpulido.ui.movies.movies.info

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antonio.pulido.pokedexpulido.R
import com.antonio.pulido.pokedexpulido.ui.composables.items.ItemInfo
import com.antonio.pulido.pokedexpulido.ui.movies.composables.buttons.LargeCustomButton
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens
import com.antonio.pulido.pokedexpulido.ui.theme.Secondary

@Composable
fun InfoMoviesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: InfoMoviesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.getState<InfoMoviesViewState>().collectAsState()
    val id = navController.previousBackStackEntry?.savedStateHandle?.get<String>("id") ?: ""
    LaunchedEffect(viewModel) {
        viewModel.onEvent(InfoMoviesViewEvent.GetInfo(id))
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = modifier.height(10.dp))
            Row(
                modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Box(
                    modifier = modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clip(CircleShape)
                        .background(Secondary)
                        .clickable {
                            navController.popBackStack()
                        }, contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left_menu),
                        contentDescription = null,
                        modifier = modifier.size(20.dp),
                        tint = Color.White
                    )
                }

                Box(
                    modifier = modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clip(CircleShape)
                        .background(Secondary)
                        .clickable {
                            viewModel.onEvent(InfoMoviesViewEvent.DeleteInfoMovie)
                        }, contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_restore_from_trash_24),
                        contentDescription = null,
                        modifier = modifier.size(20.dp),
                        tint = Color.White
                    )
                }
            }

            Text(
                text = "Información de la película",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Secondary,

                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.height(16.dp))
            Divider(
                thickness = 2.dp, color = Secondary
            )
            Spacer(modifier = modifier.height(16.dp))

            CellInfo(title = "Nombre de la película", info = uiState.pelicula.nombre ?: "")

            CellInfo(title = "Idioma", info = uiState.pelicula.idioma ?: "")

            CellInfo(title = "Descripción", info = uiState.pelicula.descripcion ?: "")

            CellInfo(title = "Duración", info = "${uiState.pelicula.duracion}")

            CellInfo(title = "Año", info = "${uiState.pelicula.year}")

            CellInfo(title = "Genero", info = "${uiState.pelicula.genero}")

            ItemInfo(title = "Actores", content = {
                uiState.actoresInvolucrados.forEachIndexed { index, actor ->
                    Text(
                        text = "${index + 1}.- Nombre: ${actor.nombre}\n\t\tNacionalidad: ${actor.nacionalidad}\n\t\tEdad: ${actor.edad}",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Secondary, fontSize = 18.sp, fontWeight = FontWeight.Light
                        ),
                        modifier = modifier.padding(horizontal = 10.dp)
                    )
                    Spacer(modifier = modifier.height(4.dp))
                }
            }, editUnion = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "id", uiState.pelicula.codigo ?: ""
                )
                navController.navigate(Screens.ADD_ACTUACION)
            })

            ItemInfo(title = "Reseñas",
                content = {
                    uiState.resenas.forEachIndexed { index, resenar ->
                        Text(
                            text = "Reseña # ${index + 1}.-\nNombre:${resenar.nombreUsuario}\n${resenar.mensaje}",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = Secondary, fontSize = 18.sp, fontWeight = FontWeight.Light
                            ),
                            modifier = modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                editUnion = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "id", uiState.pelicula.codigo ?: ""
                    )
                    navController.navigate(Screens.ADD_RESENA)
                })

            ItemInfo(title = "Directores", content = {
                uiState.directoresInvolucrados.forEachIndexed { index, director ->
                    Text(
                        text = "${index + 1}.- Nombre: ${director.nombre}\n\t\tEdad: ${director.edad}",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Secondary, fontSize = 18.sp, fontWeight = FontWeight.Light
                        ),
                        modifier = modifier.padding(horizontal = 10.dp)
                    )
                    Spacer(modifier = modifier.height(4.dp))
                }
            }, editUnion = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "id", uiState.pelicula.codigo ?: ""
                )
                navController.navigate(Screens.ADD_DIRECCIONES)
            })

            ItemInfo(title = "Producciones", editUnion = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "id", uiState.pelicula.codigo ?: ""
                )
                navController.navigate(Screens.ADD_PRODUCCION)
            }) {
                uiState.producir.forEachIndexed { index, producir ->
                    Text(
                        text = "${index + 1}.-\nNombre: ${producir.nombre}",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Secondary, fontSize = 18.sp, fontWeight = FontWeight.Light
                        ),
                        modifier = modifier.padding(horizontal = 10.dp)
                    )
                    Spacer(modifier = modifier.height(4.dp))
                }
            }


            ItemInfo(title = "¿Donde ver?", editUnion = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "id", uiState.pelicula.codigo ?: ""
                )
                navController.navigate(Screens.ADD_APP)
            }) {
                uiState.aplicaciones.forEachIndexed { index, ver ->
                    Text(
                        text = "${index + 1}.-\nNombre: ${ver.nombre}",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Secondary, fontSize = 18.sp, fontWeight = FontWeight.Light
                        ),
                        modifier = modifier.padding(horizontal = 10.dp)
                    )
                    Spacer(modifier = modifier.height(4.dp))
                }
            }

            LargeCustomButton(text = "Editar") {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "id", uiState.pelicula.codigo ?: ""
                )
                navController.navigate(Screens.UPDATE_MOVIES_SCREEN)
            }
        }
    }

    when {
        uiState.successDelete -> {
            navController.backQueue.clear()
            navController.navigate(Screens.MOVIES_SCREEN)
        }
    }
}

@Composable
fun CellInfo(modifier: Modifier = Modifier, title: String, info: String) {
    Text(
        text = title, textAlign = TextAlign.Start, style = MaterialTheme.typography.titleLarge.copy(
            color = Secondary, fontSize = 20.sp, fontWeight = FontWeight.Normal
        ), modifier = modifier.fillMaxWidth()
    )
    Spacer(modifier = modifier.height(8.dp))

    Text(
        text = info, textAlign = TextAlign.Start, style = MaterialTheme.typography.titleLarge.copy(
            color = Secondary, fontSize = 18.sp, fontWeight = FontWeight.Light
        ), modifier = modifier.padding(horizontal = 10.dp)
    )
    Spacer(modifier = modifier.height(8.dp))
}