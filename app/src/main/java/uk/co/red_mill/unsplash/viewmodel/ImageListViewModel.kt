package uk.co.red_mill.unsplash.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uk.co.red_mill.unsplash.data.model.UnsplashImage
import uk.co.red_mill.unsplash.data.repository.ImagesRepository

class ImageListViewModel(application: Application) : AndroidViewModel(application) {
    private var imageListObservable: LiveData<List<UnsplashImage>>? = null
    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */

    init {
        imageListObservable = ImagesRepository.getInstance().getImageList()
    }

    fun getImagesListObservable(): LiveData<List<UnsplashImage>>? {
        return imageListObservable
    }
}