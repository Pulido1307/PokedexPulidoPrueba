package com.antonio.pulido.pokedexpulido.ui.movies.composables.bottom.bar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.antonio.pulido.pokedexpulido.R
import com.antonio.pulido.pokedexpulido.ui.composables.nav.NavigationItem
import com.antonio.pulido.pokedexpulido.ui.navigation.Screens
import com.antonio.pulido.pokedexpulido.ui.theme.CheckBoxColor
import com.antonio.pulido.pokedexpulido.ui.theme.Secondary

data class NavigationItem(
    val title: String,
    val icon: Int,
    val route: String? = null
)

private val itemsMovies = listOf(
    NavigationItem(
        title = "Peliculas",
        icon = R.drawable.twotone_movie_24,
        route = Screens.MOVIES_SCREEN
    ),
    NavigationItem(
        title = "Actores",
        icon = R.drawable.twotone_favorite_24,
        route = Screens.ACTORES_SCREEN
    )
)

@Composable
fun BottomNavigationMovies(
    currentRoute: String,
    navController: NavController
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background,
    ) {
        itemsMovies.forEachIndexed { _, navigationItem ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(navigationItem.icon),
                        contentDescription = navigationItem.title,
                        modifier = Modifier.size(24.dp),
                        tint = Secondary
                    )
                },
                label = {
                    Text(
                        text = navigationItem.title,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Secondary
                        )
                    )
                },
                selected = currentRoute == navigationItem.route,
                onClick = {
                    navigationItem.route?.let {
                        if (currentRoute != it) {
                            navController.backQueue.clear()
                            navController.navigate(it)
                        }
                    }
                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CheckBoxColor,
                    selectedTextColor = CheckBoxColor,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}