package pe.edu.upc.musiccompose.repository

import pe.edu.upc.musiccompose.local.AlbumDao
import pe.edu.upc.musiccompose.model.Album
import pe.edu.upc.musiccompose.model.Artist
import pe.edu.upc.musiccompose.remote.AlbumService
import javax.inject.Inject


class AlbumRepository @Inject constructor(
    private val albumService: AlbumService,
    private val albumDao: AlbumDao
) {
    suspend fun fetchAlbumsByArtistId(categoryId: String): List<Album> {
        val response = albumService.fetchAlbumsByArtistId(categoryId)
        if (response.isSuccessful && response.body() != null) {
            for (item in response.body()!!.albums) {
                item.favorite = albumDao.fetchById(item.id) != null
            }
            return response.body()!!.albums
        }
        return listOf()
    }

    suspend fun insert( album: Album) {
        albumDao.insert(album)
        album.favorite = true
    }

    suspend fun delete(album: Album) {
        albumDao.delete(album)
        album.favorite = false
    }

}