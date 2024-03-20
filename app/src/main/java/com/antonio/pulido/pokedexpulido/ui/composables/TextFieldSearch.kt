package com.antonio.pulido.pokedexpulido.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antonio.pulido.pokedexpulido.ui.theme.PokedexFont
import com.antonio.pulido.pokedexpulido.ui.theme.Primary
import com.antonio.pulido.pokedexpulido.ui.theme.Secondary

@Composable
fun TextFieldSearch(
    value: String,
    onValueTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        modifier = modifier.fillMaxWidth()
            .padding(start = 10.dp),
        onValueChange = onValueTextChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Primary,
            unfocusedTextColor = Primary,
            cursorColor = Primary,
            errorTextColor = Color.Red,
            unfocusedBorderColor = Primary,
            focusedBorderColor = Primary,
            errorContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
        ),
        shape = RoundedCornerShape(25.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Secondary
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        textStyle = TextStyle.Default.copy(fontSize = 10.sp, fontFamily = PokedexFont),
        placeholder = {
            Text(
                text = "Buscar",
                style = TextStyle.Default.copy(
                    fontSize = 10.sp,
                    fontFamily = PokedexFont,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    )
}