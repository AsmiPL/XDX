package com.example.spotifyclone.data

import com.example.spotifyclone.network.SpotifyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpotifyRepository(
    private val service: SpotifyService = SpotifyService()
) {
    suspend fun fetchFeaturedPlaylists(): Result<List<String>> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            val token = service.fetchToken()
            val response = service.fetchFeaturedPlaylists(token)
            response.playlists.items.map { it.name }
        }
    }
}
