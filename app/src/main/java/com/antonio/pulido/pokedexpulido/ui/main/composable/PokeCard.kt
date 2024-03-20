package com.antonio.pulido.pokedexpulido.ui.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import com.antonio.pulido.pokedexpulido.ui.theme.Primary
import com.antonio.pulido.pokedexpulido.ui.theme.Secondary
import com.antonio.pulido.web.ApiConstants

@Composable
fun PokeCard(
    modifier: Modifier = Modifier,
    url: String,
    nombre: String
) {
    val parts = url.split("/")

    val pokemonNumber = parts[parts.size - 2].toInt()

    Card(
        modifier
            .shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(25.dp)
            )
            .fillMaxWidth(1f)
            .padding(10.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Primary
        )
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ApiConstants.getPokeImage(pokemonNumber),
                contentDescription = "Imagen pokedex",
                modifier = modifier.size(70.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.height(10.dp))
            Box(modifier = modifier.background(Secondary).padding(10.dp)){
                Text(
                   text = "#$pokemonNumber $nombre",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}