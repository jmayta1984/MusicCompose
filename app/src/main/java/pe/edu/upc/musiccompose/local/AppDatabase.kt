package pe.edu.upc.musiccompose.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upc.musiccompose.model.Album

@Database(entities = [Album::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}