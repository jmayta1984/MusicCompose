package pe.edu.upc.musiccompose.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.upc.musiccompose.model.Album

@Dao
interface AlbumDao {
    @Insert
    suspend fun insert(vararg album: Album)

    @Delete
    suspend fun delete(vararg album: Album)

    @Query("select * from albums where id=:id")
    suspend fun fetchById(id: String): Album?
}