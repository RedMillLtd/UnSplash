package uk.co.red_mill.unsplash.view.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import uk.co.red_mill.unsplash.R

object ImageBinder {

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso.get()
            .load(imageUrl)
            .error(R.drawable.placeholder_image)
            .placeholder(R.drawable.placeholder_image)
            .into(view)
    }

    @BindingAdapter("app:imageUrlForPalette")
    @JvmStatic
    fun setBackgroundColor(view: TextView, imageUrl: String) {
        val target: Target = object: Target {
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }
            override fun onBitmapLoaded(bitmap: Bitmap, from:Picasso.LoadedFrom) {
                Palette.from(bitmap).generate { palette ->
                    val vibrant = palette?.swatches?.get(0)
                    view.setBackgroundColor(vibrant?.rgb ?: ContextCompat.getColor(view.context, android.R.color.holo_green_dark))
                    view.setTextColor(vibrant?.titleTextColor ?: ContextCompat.getColor(view.context, android.R.color.black))
                }
            }
            override fun onPrepareLoad(placeHolderDrawable: Drawable) {
            }
        }

        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(target)
    }
}