package uk.co.red_mill.unsplash.view.callback

import uk.co.red_mill.unsplash.data.model.UnsplashImage

interface ImageCallback {
    fun onClick(image: UnsplashImage)
}