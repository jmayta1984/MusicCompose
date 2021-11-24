package pe.edu.upc.musiccompose.ui.screens.artists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.musiccompose.model.Artist
import pe.edu.upc.musiccompose.repository.ArtistRepository
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val artistRepository: ArtistRepository
) :
    ViewModel() {
    private val _artists = MutableLiveData<List<Artist>>()
    val artists get() = _artists

    private val _name = MutableLiveData<String>()
    val name get() = _name

    fun fetchArtistsByName(name: String) {
        viewModelScope.launch {
            _artists.postValue(artistRepository.fetchArtistsByName(name))
        }
    }

    fun updateName(name: String) {
        _name.postValue(name)
    }
}