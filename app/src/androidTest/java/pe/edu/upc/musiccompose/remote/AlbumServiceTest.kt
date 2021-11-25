package pe.edu.upc.musiccompose.remote

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pe.edu.upc.musiccompose.MockServer
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

    @After
    fun tearDown() {
        MockServer.server.shutdown()
    }

    @Test
    fun fetchAlbumsByArtistName() = runBlocking {
        val artist = "coldplay"
        val response = albumService.fetchAlbumsByArtistName(artist)
        val results = response.body()!!.albums
        assertThat(results).isNotEmpty()
    }

}