package pe.edu.upc.musiccompose.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    @GET("searchalbum.php")
    suspend fun fetchAlbumsByArtistId(@Query("s") artistId: String): Response<AlbumResponse>
}