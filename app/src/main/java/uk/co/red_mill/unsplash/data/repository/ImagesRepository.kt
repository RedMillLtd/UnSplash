package uk.co.red_mill.unsplash.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.red_mill.unsplash.BuildConfig
import uk.co.red_mill.unsplash.data.model.UnsplashImage

class ImagesRepository {
    private var unsplashService: UnsplashService? = null
    private val PAGES_TO_DISPLAY: Int = 30

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(UnsplashService.HTTPS_API_UNSPLASH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            unsplashService = retrofit.create(UnsplashService::class.java)
    }

    fun getImageList(): LiveData<List<UnsplashImage>> {
        val data = MutableLiveData<List<UnsplashImage>>()
        val apiKey: String = BuildConfig.UnsplashAPIKEY

        unsplashService?.getPhotos(apiKey, PAGES_TO_DISPLAY)?.enqueue(object : Callback<List<UnsplashImage>> {
            override fun onResponse(call: Call<List<UnsplashImage>>, response: Response<List<UnsplashImage>>) {
                data.setValue(response.body())
            }
            override fun onFailure(call: Call<List<UnsplashImage>>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        return data
    }

    companion object {
        @Volatile
        private var INSTANCE: ImagesRepository? = null

        fun getInstance(): ImagesRepository =
            INSTANCE ?: synchronized(this) {
                ImagesRepository()
            }
    }
}