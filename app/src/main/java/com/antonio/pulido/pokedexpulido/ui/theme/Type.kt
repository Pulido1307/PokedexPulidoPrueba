package com.antonio.pulido.pokedexpulido.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.antonio.pulido.pokedexpulido.R

val PokedexFont = FontFamily(
    Font(R.font.mavenpro_bold, FontWeight.Bold),
    Font(R.font.mavenpro_medium, FontWeight.Medium),
    Font(R.font.mavenpro_regular, FontWeight.Normal)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = PokedexFont,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        letterSpacing = 0.sp,
        color = TextColor
    ),
    bodyMedium = TextStyle(
        fontFamily = PokedexFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.sp,
        color = Color.White
    ),
    bodySmall = TextStyle(
        fontFamily = PokedexFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.sp,
        color = Color.White
    ),
    labelLarge = TextStyle(
        fontFamily = PokedexFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = PokedexFont,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        letterSpacing = 0.sp,
        color = TextColor
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        color =  TextColor
    )
)