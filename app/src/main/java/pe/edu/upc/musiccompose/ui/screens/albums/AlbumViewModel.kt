package pe.edu.upc.musiccompose.ui.screens.albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.musiccompose.model.Album
import pe.edu.upc.musiccompose.repository.AlbumRepository
import pe.edu.upc.musiccompose.repository.ArtistRepository
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : ViewModel() {
    private val _albums = MutableLiveData<List<Album>>()
    val albums get() = _albums

    fun fetchAlbumsByArtistName(artistName: String) {
        viewModelScope.launch {
            _albums.postValue(albumRepository.fetchAlbumsByArtistName(artistName))
        }
    }

    fun insert(album: Album) {
        viewModelScope.launch {
            albumRepository.insert(album)
        }
    }

    fun delete(album: Album) {
        viewModelScope.launch {
            albumRepository.delete(album)
        }
    }

}