package com.antonio.pulido.pokedexpulido.ui.movies.composables.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.antonio.pulido.pokedexpulido.ui.theme.Secondary

@Composable
fun LargeCustomButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Secondary,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    enabled: Boolean = true,
    style : TextStyle = MaterialTheme.typography.labelLarge,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
            style = style,
        )
    }
}