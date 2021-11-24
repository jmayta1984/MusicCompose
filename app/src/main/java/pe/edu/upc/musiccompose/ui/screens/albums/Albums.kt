package pe.edu.upc.musiccompose.ui.screens.albums

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pe.edu.upc.musiccompose.model.Album

@Composable
fun Albums(viewModel: AlbumViewModel) {
    AlbumList(viewModel)
}

@Composable
fun AlbumList(
    viewModel: AlbumViewModel
) {
    val albums: List<Album> by viewModel.albums.observeAsState(listOf())

    LazyColumn {
        items(albums) { album ->
            AlbumRow(
                album,
                insert = { viewModel.insert(album) },
                delete = { viewModel.delete(album) }
            )
        }
    }
}

@Composable
fun AlbumRow(
    album: Album,
    insert: () -> Unit,
    delete: () -> Unit
) {
    var favorite by remember { mutableStateOf(false) }
    favorite = album.favorite

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        Row {
            AlbumImage(album)
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(7f)) {
                Text(album.name, fontWeight = FontWeight.Bold)
                Text(album.artistName)
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    if (favorite) delete() else insert()
                    favorite = !favorite
                }) {
                Icon(
                    Icons.Filled.Favorite,
                    null,
                    tint = if (favorite) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
        }
    }
}


@Composable
fun AlbumImage(album: Album) {
    Image(
        painter = rememberImagePainter(album.poster),
        contentDescription = null,
        modifier = Modifier
            .size(92.dp)
            .clip(shape = RoundedCornerShape(4.dp)),
        contentScale = ContentScale.Crop
    )
}