package uk.co.red_mill.unsplash.di


import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.red_mill.unsplash.data.repository.UnsplashService
import uk.co.red_mill.unsplash.viewmodel.ImageViewModelFactory
import javax.inject.Singleton

@Module(subcomponents = [ViewModelSubComponent::class])
internal class AppModule {
    @Singleton
    @Provides
    fun provideUnsplashService(): UnsplashService {
        return Retrofit.Builder()
            .baseUrl(UnsplashService.HTTPS_API_UNSPLASH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashService::class.java)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(
        viewModelSubComponent: ViewModelSubComponent.Builder
    ): ViewModelProvider.Factory {

        return ImageViewModelFactory(viewModelSubComponent.build())
    }
}