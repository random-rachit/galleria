package com.rachitbhutani.galleria.gallery.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rachitbhutani.galleria.data.ImageData
import com.rachitbhutani.galleria.gallery.data.GalleryRepository
import javax.inject.Inject

class GalleryPagingSource @Inject constructor(private val repository: GalleryRepository) :
    PagingSource<Long, ImageData>() {

    private var nextKey: Long? = 0
    private var pageNo = 0

    override fun getRefreshKey(state: PagingState<Long, ImageData>): Long? {
        return null
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, ImageData> {
        return try {
            params.loadSize
            val imageList = repository.getImagesFromGallery(pageNo)
            if (nextKey == imageList.last().id) {
                return LoadResult.Error(Throwable("End of data"))
            }
            nextKey = imageList.last().id
            pageNo++
            LoadResult.Page(imageList, null, imageList.last().id)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}