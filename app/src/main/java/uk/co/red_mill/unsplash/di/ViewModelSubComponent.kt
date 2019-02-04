package uk.co.red_mill.unsplash.di

import dagger.Subcomponent
import uk.co.red_mill.unsplash.viewmodel.ImageListViewModel

/**
 * A sub component to create ViewModels. It is called by the
 * [uk.co.red_mill.unsplash.viewmodel.ImageViewModelFactory].
 */
@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun imageListViewModel(): ImageListViewModel
}
