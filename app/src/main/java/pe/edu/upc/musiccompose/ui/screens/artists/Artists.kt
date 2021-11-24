package pe.edu.upc.musiccompose.ui.screens.artists

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import pe.edu.upc.musiccompose.model.Artist

@ExperimentalFoundationApi
@Composable
fun Artists(
    viewModel: ArtistsViewModel = hiltViewModel(),
    selectItem: (categoryId: String) -> Unit
) {
    Column {
        ArtistSearch(viewModel = viewModel)
        ArtistGrid(viewModel = viewModel, selectItem)
    }
}

@ExperimentalFoundationApi
@Composable
fun ArtistGrid(viewModel: ArtistsViewModel, selectItem: (categoryId: String) -> Unit) {
    val artists by viewModel.artists.observeAsState(emptyList())

    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(artists) { artist ->
            ArtistItem(artist, selectItem)
        }
    }
}

@Composable
fun ArtistSearch(viewModel: ArtistsViewModel) {
    val name: String by viewModel.name.observeAsState("")
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        value = name,
        onValueChange = {
            viewModel.updateName(it)
        },
        leadingIcon = {
            Icon(Icons.Filled.Search, null)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                viewModel.fetchArtistsByName(name)
                focusManager.clearFocus()
            }
        )
    )
}

@Composable
fun ArtistItem(artist: Artist, selectItem: (artistId: String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp),
        elevation = 2.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    selectItem(artist.name)
                }) {

            ArtistImage(artist)
            Text(artist.name)
        }
    }
}

@Composable
fun ArtistImage(artist: Artist) {
    Image(
        painter = rememberImagePainter(artist.poster),
        contentDescription = null,
        modifier = Modifier.size(128.dp)
    )
}