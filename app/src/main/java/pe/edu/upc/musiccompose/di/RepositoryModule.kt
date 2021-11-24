package pe.edu.upc.musiccompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pe.edu.upc.musiccompose.remote.ArtistService
import pe.edu.upc.musiccompose.repository.ArtistRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideArtistRepository(
        artistService: ArtistService

    ): ArtistRepository {
        return ArtistRepository(artistService)
    }
}