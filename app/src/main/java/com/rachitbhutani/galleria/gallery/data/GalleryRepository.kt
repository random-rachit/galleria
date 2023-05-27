package com.rachitbhutani.galleria.gallery.data

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.ContentResolverCompat
import androidx.core.os.bundleOf
import com.rachitbhutani.galleria.data.ImageData
import javax.inject.Inject

class GalleryRepository @Inject constructor(private val contentResolver: ContentResolver) {

    fun getImagesFromGallery(page: Int): List<ImageData> {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_TAKEN
        )

        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

        val selection = Bundle().apply {
            putInt(ContentResolver.QUERY_ARG_LIMIT, 20)
            putInt(ContentResolver.QUERY_ARG_OFFSET, page * 20)
            putString(ContentResolver.QUERY_ARG_SQL_SORT_ORDER, sortOrder)
        }

        val query = contentResolver.query(collection, projection, selection, null)

        query?.use { cursor ->
            val imageList = mutableListOf<ImageData>()
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            var pageSize = 0
            while (cursor.moveToNext() && pageSize < 10) {
                pageSize++
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val date = cursor.getString(dateColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                val imageData = ImageData(id, contentUri, name, date)
                imageList.add(imageData)
            }
            return imageList
        }
        return emptyList()
    }

}