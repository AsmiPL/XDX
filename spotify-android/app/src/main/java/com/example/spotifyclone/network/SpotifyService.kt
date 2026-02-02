package com.example.spotifyclone.network

import android.util.Base64
import com.example.spotifyclone.BuildConfig
import com.example.spotifyclone.model.FeaturedPlaylistsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SpotifyService {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val authRetrofit = Retrofit.Builder()
        .baseUrl("https://accounts.spotify.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val apiRetrofit = Retrofit.Builder()
        .baseUrl("https://api.spotify.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val authApi = authRetrofit.create(SpotifyAuthApi::class.java)
    private val spotifyApi = apiRetrofit.create(SpotifyApi::class.java)

    suspend fun fetchToken(): String {
        val credentials = "${BuildConfig.SPOTIFY_CLIENT_ID}:${BuildConfig.SPOTIFY_CLIENT_SECRET}"
        val encoded = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        val response = authApi.getToken("Basic $encoded", "client_credentials")
        return response.accessToken
    }

    suspend fun fetchFeaturedPlaylists(token: String): FeaturedPlaylistsResponse {
        return spotifyApi.getFeaturedPlaylists("Bearer $token")
    }
}
