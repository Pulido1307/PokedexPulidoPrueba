package com.antonio.pulido.pokedexpulido.ui.composables.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.antonio.pulido.pokedexpulido.R
import kotlinx.coroutines.delay

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    isVisible: Boolean
) {
    if (isVisible) {
        Dialog(onDismissRequest = { }) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    CircularProgressIndicator(color = Color.White, modifier = modifier.size(50.dp))
                    RotatingImage()
                    Spacer(modifier = modifier.height(16.dp))
                    Text(
                        text = "Cargando...",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                    )
                }

            }
        }
    }
}

@Composable
fun RotatingImage() {
    var rotation by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            rotation += 10f
            delay(16)
        }
    }

    Box(modifier = Modifier.size(100.dp)) {
        Image(
            painter = painterResource(id = R.drawable.pokebola),
            contentDescription = null,
            modifier = Modifier.rotate(rotation)
        )
    }
}