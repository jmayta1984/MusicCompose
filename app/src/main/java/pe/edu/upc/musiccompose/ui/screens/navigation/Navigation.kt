package pe.edu.upc.musiccompose.ui.screens.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.musiccompose.ui.screens.albums.AlbumViewModel
import pe.edu.upc.musiccompose.ui.screens.albums.Albums
import pe.edu.upc.musiccompose.ui.screens.artists.Artists

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    Scaffold {
        NavHost(navController = navController, startDestination = Routes.Artists.route) {
            composable(Routes.Artists.route) {
                Artists {
                    navController.navigate("${Routes.Albums.route}/$it")
                }
            }
            composable(
                Routes.Albums.routeWithArgument,
                arguments = listOf(navArgument(Routes.Albums.argument) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val viewModel: AlbumViewModel = hiltViewModel()

                val artistId = backStackEntry.arguments!!.getString(
                    Routes.Albums.argument, ""
                )
                viewModel.fetchAlbumsByArtistId(artistId)
                Albums(
                    viewModel
                )
            }
            composable(Routes.Tracks.route) {

            }
        }

    }
}

sealed class Routes(val route: String) {
    object Artists : Routes("Artists")
    object Albums : Routes("Albums") {
        const val routeWithArgument = "Albums/{artistId}"
        const val argument = "artistId"
    }

    object Tracks : Routes("Tracks")
}