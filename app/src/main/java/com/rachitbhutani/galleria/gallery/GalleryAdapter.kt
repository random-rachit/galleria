package com.rachitbhutani.galleria.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachitbhutani.galleria.data.ImageData
import com.rachitbhutani.galleria.databinding.GalleryItemBinding
import javax.inject.Inject

class GalleryAdapter @Inject constructor(val listener: GalleryAdapterListener) :
    PagingDataAdapter<ImageData, GalleryAdapter.Holder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImageData>() {
            override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(GalleryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindImage(getItem(position))
    }

    inner class Holder(private val binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindImage(data: ImageData?) {
            binding.run {
                Glide.with(root.context).load(data?.uri).into(ivContent)
                root.setOnClickListener {
                    listener.onItemClick(data?.uri?.toString())
                }
            }
        }
    }
}