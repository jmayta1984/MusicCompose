package pe.edu.upc.musiccompose.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {
    @GET("search.php")
    suspend fun fetchArtistsByName(@Query("s") name: String): Response<ArtistResponse>
}