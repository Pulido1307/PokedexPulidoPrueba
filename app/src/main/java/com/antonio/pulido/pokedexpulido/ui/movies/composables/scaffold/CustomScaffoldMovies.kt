package com.antonio.pulido.pokedexpulido.ui.movies.composables.scaffold

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.antonio.pulido.pokedexpulido.R
import com.antonio.pulido.pokedexpulido.ui.movies.composables.bottom.bar.BottomNavigationMovies
import com.antonio.pulido.pokedexpulido.ui.theme.Secondary

@Composable
fun CustomScaffoldWithNavMovies(
    currentRoute: String,
    navController: NavController,
    clickFloatingButton: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationMovies(currentRoute = currentRoute, navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = clickFloatingButton, containerColor = Secondary) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.twotone_note_add_24),
                    contentDescription = "My FAB Icon",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}