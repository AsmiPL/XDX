package com.example.spotifyclone.network

import com.example.spotifyclone.model.FeaturedPlaylistsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface SpotifyApi {
    @GET("v1/browse/featured-playlists?limit=20")
    suspend fun getFeaturedPlaylists(
        @Header("Authorization") authorization: String
    ): FeaturedPlaylistsResponse
}
