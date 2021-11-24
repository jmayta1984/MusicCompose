package pe.edu.upc.musiccompose.model

import com.google.gson.annotations.SerializedName

class Artist(
    @SerializedName("idArtist")
    val id: String,
    @SerializedName("strArtist")
    val name: String,
    @SerializedName("strArtistThumb")
    val poster: String
)