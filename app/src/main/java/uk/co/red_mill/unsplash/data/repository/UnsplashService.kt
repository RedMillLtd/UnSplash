package uk.co.red_mill.unsplash.data.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.red_mill.unsplash.data.model.UnsplashImage

interface UnsplashService {

    @GET("photos")
    fun getPhotos(
        @Query("client_id") id: String,
        @Query("per_page") per_page: Int
    ) : Call<List<UnsplashImage>>

    companion object {
        val HTTPS_API_UNSPLASH_URL = "https://api.unsplash.com/"
    }
}