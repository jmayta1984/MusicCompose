package pe.edu.upc.musiccompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upc.musiccompose.model.Artist
import pe.edu.upc.musiccompose.remote.AlbumService
import pe.edu.upc.musiccompose.remote.ArtistService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    private const val API_BASE_URL = "https://www.theaudiodb.com/api/v1/json/523532/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideArtistService(retrofit: Retrofit): ArtistService {
        return retrofit.create(ArtistService::class.java)
    }

    @Provides
    @Singleton
    fun provideAlbumService(retrofit: Retrofit): AlbumService {
        return retrofit.create(AlbumService::class.java)
    }
}