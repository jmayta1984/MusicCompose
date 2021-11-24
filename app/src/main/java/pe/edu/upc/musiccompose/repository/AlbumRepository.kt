package pe.edu.upc.musiccompose.repository

import pe.edu.upc.musiccompose.model.Album
import pe.edu.upc.musiccompose.model.Artist
import pe.edu.upc.musiccompose.remote.AlbumService
import javax.inject.Inject


class AlbumRepository @Inject constructor(
    private val albumService: AlbumService
) {
    suspend fun fetchAlbumsByArtistId(categoryId: String): List<Album> {
        val response = albumService.fetchAlbumsByArtistId(categoryId)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!.albums
        }
        return listOf()
    }
}