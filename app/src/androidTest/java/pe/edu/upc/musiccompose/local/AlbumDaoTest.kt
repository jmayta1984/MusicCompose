package pe.edu.upc.musiccompose.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pe.edu.upc.musiccompose.model.Album
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class AlbumDaoTest {

    @get: Rule
    val hiltRule = HiltAndroidRule(this)

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test.db")
    lateinit var database: AppDatabase
    private lateinit var albumDao: AlbumDao

    @Before
    fun setUp(){
        hiltRule.inject()
        albumDao = database.albumDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insert() = runBlockingTest {
        val album = Album("1", "October", "U2", "https://www.theaudiodb.com/images/media/album/thumb/qurprp1552965363.jpg")
        albumDao.insert(album)

        val favoriteAlbum = albumDao.fetchById("1")
        assertThat(favoriteAlbum).isNotNull()
    }


    @Test
    fun delete() = runBlockingTest {
        val album = Album("2", "October", "U2", "https://www.theaudiodb.com/images/media/album/thumb/qurprp1552965363.jpg")
        albumDao.insert(album)
        albumDao.delete(album)
        val favoriteAlbum = albumDao.fetchById("2")
        assertThat(favoriteAlbum).isNull()
    }

}