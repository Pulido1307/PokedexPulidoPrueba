package com.antonio.pulido.pokedexpulido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.antonio.pulido.pokedexpulido.ui.navigation.Navigation
import com.antonio.pulido.pokedexpulido.ui.theme.PokedexPulidoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexPulidoTheme {
                Navigation(navController = rememberNavController())
            }
        }
    }
}
