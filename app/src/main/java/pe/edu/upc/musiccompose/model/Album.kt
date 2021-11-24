package pe.edu.upc.musiccompose.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "albums")
class Album(
    @PrimaryKey
    @SerializedName("idAlbum")
    val id: String,
    @SerializedName("strAlbum")
    val name: String,
    @SerializedName("strArtist")
    val artistName: String,
    @SerializedName("strAlbumThumb")
    val poster: String
) {
    @Ignore
    var favorite: Boolean = false
}