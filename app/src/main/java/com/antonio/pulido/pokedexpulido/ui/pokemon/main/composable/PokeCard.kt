package com.antonio.pulido.pokedexpulido.ui.pokemon.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.antonio.pulido.pokedexpulido.ui.theme.PrimaryCard

import com.antonio.pulido.pokedexpulido.ui.theme.Secondary
import com.antonio.pulido.web.ApiConstants

@Composable
fun PokeCard(
    modifier: Modifier = Modifier,
    id: Int,
    nombre: String,
    onClick: () -> Unit
) {
    Card(
        modifier
            .shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(25.dp)
            )
            .fillMaxWidth(1f)
            .padding(10.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryCard
        )
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ApiConstants.getPokeImage(id),
                contentDescription = "Imagen pokedex",
                modifier = modifier.size(70.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.height(10.dp))
            Box(
                modifier = modifier
                    .background(Secondary)
                    .padding(10.dp)
            ) {
                Text(
                    text = "#$id $nombre",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}