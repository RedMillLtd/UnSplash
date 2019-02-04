package uk.co.red_mill.unsplash.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import uk.co.red_mill.unsplash.R
import uk.co.red_mill.unsplash.data.model.UnsplashImage
import uk.co.red_mill.unsplash.databinding.ImageListFragmentBinding
import uk.co.red_mill.unsplash.view.adapter.ImagesAdapter
import uk.co.red_mill.unsplash.view.callback.ImageCallback
import uk.co.red_mill.unsplash.viewmodel.ImageListViewModel
import uk.co.red_mill.unsplash.viewmodel.ImageViewModelFactory
import javax.inject.Inject

class ImageListFragment : Fragment() {
    private var projectAdapter: ImagesAdapter? = null
    private var binding: ImageListFragmentBinding? = null

    private val projectClickCallback = object : ImageCallback {
        override fun onClick(image: UnsplashImage) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                //Do something with click
            }
        }
    }

    @Inject
    val viewModelFactory: ImageViewModelFactory? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.image_list_fragment, container, false)

        projectAdapter = ImagesAdapter(projectClickCallback)
        binding!!.imageList.adapter = projectAdapter
        binding!!.isLoading = true

        return binding!!.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(ImageListViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ImageListViewModel) {
        // Update the list when the data changes
        viewModel.getImagesListObservable()!!.observe(this,
            Observer<List<UnsplashImage>> { projects ->
                if (projects != null) {
                    binding!!.isLoading = false
                    projectAdapter!!.setImageList(projects)
                }
            })
    }

    companion object {
        const val TAG = "ImageListFragment"
    }
}