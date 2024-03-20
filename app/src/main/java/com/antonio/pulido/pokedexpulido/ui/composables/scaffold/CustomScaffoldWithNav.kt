package com.antonio.pulido.pokedexpulido.ui.composables.scaffold

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.antonio.pulido.pokedexpulido.ui.composables.nav.BottomNavigation
import com.antonio.pulido.pokedexpulido.ui.theme.SurfaceBackground

@Composable
fun CustomScaffoldWithNav(
    currentRoute: String,
    navController: NavController,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(currentRoute = currentRoute, navController = navController)
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = SurfaceBackground
        ) {
            content()
        }
    }
}