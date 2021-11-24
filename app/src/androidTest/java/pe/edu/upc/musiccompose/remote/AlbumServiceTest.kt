package pe.edu.upc.musiccompose.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
class AlbumServiceTest {
    @get: Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var albumService: AlbumService

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun fetchAlbumsByArtist() = runBlocking {
        val artist = "coldplay"
        val response = albumService.fetchAlbumsByArtistId(artist)
        val results = response.body()!!.albums
        assertThat(results).isNotEmpty()
    }

}