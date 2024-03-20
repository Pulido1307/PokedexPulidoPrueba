package com.antonio.pulido.pokedexpulido.ui.info.local

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.antonio.pulido.pokedexpulido.R
import com.antonio.pulido.pokedexpulido.ui.theme.PrimaryCard
import com.antonio.pulido.pokedexpulido.ui.theme.Secondary
import com.antonio.pulido.pokedexpulido.ui.theme.TextColor
import com.antonio.pulido.web.ApiConstants

@Composable
fun InfoLocalScreen(
    modifier: Modifier = Modifier,
    viewModel: InfoLocalViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState by viewModel.getState<InfoLocalViewState>().collectAsState()
    val id = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("id") ?: 0

    LaunchedEffect(viewModel) {
        viewModel.onEvent(InfoLocalViewEvent.GetPokeInfo(id))
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = modifier
                    .fillMaxHeight(0.35f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 50.dp))
                    .background(PrimaryCard)
            ) {
                Column(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_left_menu),
                                contentDescription = null,
                                modifier = modifier
                                    .size(30.dp)
                                    .padding(top = 5.dp),
                                tint = Color.White
                            )
                        }
                    }

                    AsyncImage(
                        model = ApiConstants.getPokeImage(uiState.pokemon.id),
                        contentDescription = "",
                        modifier = modifier.weight(1f)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "",
                    modifier
                        .size(100.dp)
                        .alpha(0.2f)
                        .align(Alignment.BottomStart)
                )
            }

            Spacer(modifier = modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "#${uiState.pokemon.id}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Secondary,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = uiState.pokemon.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = TextColor,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Spacer(modifier = modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .heightIn(min = 0.dp, max = 400.dp)
                    .padding(horizontal = 16.dp),
            ) {
                items(uiState.type) {
                    Box(modifier = modifier.padding(horizontal = 10.dp)) {
                        Box(
                            modifier = modifier
                                .width(150.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Secondary)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = it,
                                modifier = modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }

            Spacer(modifier = modifier.height(16.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.kg),
                        contentDescription = "",
                        modifier = modifier.size(40.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = modifier.width(8.dp))

                    Text(
                        text = "${uiState.pokemon.weight} Kg",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextColor,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }

                Row(modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.altura),
                        contentDescription = "",
                        modifier = modifier.size(40.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = modifier.width(8.dp))

                    Text(
                        text = "${uiState.pokemon.height}m",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextColor,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}