package com.example.spotifyclone.model

import com.squareup.moshi.Json

data class FeaturedPlaylistsResponse(
    @Json(name = "message") val message: String,
    @Json(name = "playlists") val playlists: Playlists
)

data class Playlists(
    @Json(name = "items") val items: List<PlaylistItem>
)

data class PlaylistItem(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String
)
