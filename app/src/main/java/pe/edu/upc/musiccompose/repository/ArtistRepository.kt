package pe.edu.upc.musiccompose.repository

import pe.edu.upc.musiccompose.model.Artist
import pe.edu.upc.musiccompose.remote.ArtistService
import javax.inject.Inject

class ArtistRepository @Inject constructor(
    private val artistService: ArtistService,
) {
    suspend fun fetchArtistsByName(name: String): List<Artist> {
        val response = artistService.fetchArtistsByName(name)
        if (response.isSuccessful && response.body() != null) {
            response.body()!!.artists?.let {
                return it
            }
        }
        return listOf()
    }
}