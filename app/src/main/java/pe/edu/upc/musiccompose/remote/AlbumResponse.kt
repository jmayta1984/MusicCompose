package pe.edu.upc.musiccompose.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.musiccompose.model.Album

class AlbumResponse(
    @SerializedName("album")
    val albums: List<Album>
)