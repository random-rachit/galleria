package com.rachitbhutani.galleria.gallery

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rachitbhutani.galleria.gallery.data.GalleryRepository
import com.rachitbhutani.galleria.gallery.data.paging.GalleryPagingSource
import com.rachitbhutani.galleria.utils.PagingConfigConstants.INITIAL_LOAD_SIZE
import com.rachitbhutani.galleria.utils.PagingConfigConstants.LOAD_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: GalleryRepository) :
    ViewModel() {

    val galleryData = Pager(
        PagingConfig(LOAD_SIZE, INITIAL_LOAD_SIZE)
    ) { GalleryPagingSource(repository) }.flow

}