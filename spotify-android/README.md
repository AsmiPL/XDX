# Spotify Clone (Android 7+)

Minimalna aplikacja „spotify-like” w Kotlinie z użyciem Spotify Web API (Client ID + Client Secret).

## Konfiguracja

1. W `spotify-android/gradle.properties` wstaw swoje dane:
   ```
   SPOTIFY_CLIENT_ID=twoj_client_id
   SPOTIFY_CLIENT_SECRET=twoj_client_secret
   ```
2. Otwórz folder `spotify-android` w Android Studio.
3. Kliknij **Sync** i uruchom aplikację.

## Co robi aplikacja

- Pobiera token w modelu `client_credentials`.
- Wyświetla listę nazw polecanych playlist z Spotify.

> Uwaga: w aplikacjach produkcyjnych nie powinno się przechowywać `client_secret` w kliencie mobilnym. Tu jest to edukacyjny przykład wymagany w zadaniu.
