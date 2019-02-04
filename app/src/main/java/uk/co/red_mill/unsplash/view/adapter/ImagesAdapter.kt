package uk.co.red_mill.unsplash.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uk.co.red_mill.unsplash.R
import uk.co.red_mill.unsplash.data.model.UnsplashImage
import uk.co.red_mill.unsplash.databinding.ListImageItemBinding
import uk.co.red_mill.unsplash.view.callback.ImageCallback

class ImagesAdapter(
    @param:Nullable @field:Nullable
    private val imageClickCallback: ImageCallback
) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    internal var imageList: List<UnsplashImage>? = null

    fun setImageList(projectList: List<UnsplashImage>) {
        if (this.imageList == null) {
            this.imageList = projectList
            notifyItemRangeInserted(0, projectList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@ImagesAdapter.imageList!!.size
                }

                override fun getNewListSize(): Int {
                    return projectList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@ImagesAdapter.imageList!![oldItemPosition].id === projectList[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val project = projectList[newItemPosition]
                    val old = projectList[oldItemPosition]
                    return project.id === old.id && project.id == old.id
                }
            })
            this.imageList = projectList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {

        val binding: ListImageItemBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context), R.layout.list_image_item,
                parent, false)

        binding.callback = imageClickCallback

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesAdapter.ImageViewHolder, position: Int) {
        holder.binding.setImage(imageList!![position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (imageList == null) 0 else imageList!!.size
    }

    class ImageViewHolder(val binding: uk.co.red_mill.unsplash.databinding.ListImageItemBinding) : RecyclerView.ViewHolder(binding.getRoot())
}