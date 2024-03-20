package com.antonio.pulido.pokedexpulido.ui.theme.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.antonio.pulido.pokedexpulido.ui.theme.BackgroundDarks
import com.antonio.pulido.pokedexpulido.ui.theme.BackgroundLight
import com.antonio.pulido.pokedexpulido.ui.theme.PrimaryCard
import com.antonio.pulido.pokedexpulido.ui.theme.Typography

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryCard,
    secondary = BackgroundDarks,
    background = BackgroundDarks,
    surface = BackgroundDarks
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryCard,
    secondary = PrimaryCard,
    background = BackgroundLight,
    surface = PrimaryCard
)

@Composable
fun PokedexPulidoTheme(
    viewModel: ThemeViewModel = hiltViewModel(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val uiState by viewModel.getState<ThemeViewState>().collectAsState()
    viewModel.onEvent(ThemeViewEvent.GetTheme)

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (uiState.isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        uiState.isDarkMode -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor =  PrimaryCard.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = uiState.isDarkMode
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}