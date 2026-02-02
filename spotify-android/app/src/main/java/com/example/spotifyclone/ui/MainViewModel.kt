package com.example.spotifyclone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.data.SpotifyRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: SpotifyRepository = SpotifyRepository()
) : ViewModel() {
    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    fun loadFeaturedPlaylists() {
        _uiState.value = UiState(isLoading = true, message = "Ładowanie…")
        viewModelScope.launch {
            val result = repository.fetchFeaturedPlaylists()
            _uiState.value = UiState(
                isLoading = false,
                message = result.fold(
                    onSuccess = { playlists ->
                        if (playlists.isEmpty()) {
                            "Brak playlist do wyświetlenia."
                        } else {
                            playlists.joinToString(separator = "\n")
                        }
                    },
                    onFailure = { throwable ->
                        "Błąd: ${throwable.message ?: "Nieznany"}"
                    }
                )
            )
        }
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val message: String = "Kliknij \"Pobierz playlisty\" aby użyć Spotify API."
)
