package com.rachitbhutani.galleria.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rachitbhutani.galleria.R
import com.rachitbhutani.galleria.databinding.FragmentGalleryBinding
import com.rachitbhutani.galleria.utils.EmptyView
import com.rachitbhutani.galleria.utils.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class GalleryFragment : Fragment(), GalleryAdapterListener {

    private lateinit var binding: FragmentGalleryBinding

    private var mAdapter: GalleryAdapter? = null

    private val viewModel: GalleryViewModel by viewModels()

    @Inject
    lateinit var permissionManager: PermissionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupPermissionManager()
    }

    private fun setupPermissionManager() {
        permissionManager.setActions(grantAction = {
            binding.viewEmpty.isVisible = false
            loadImages()
        }, declineAction = {
            showEmptyView(getString(R.string.permission_explanation), true)
        })
        if (permissionManager.hasPermission()) loadImages()
        else showEmptyView(getString(R.string.permission_explanation), true)
    }

    private fun showEmptyView(message: String, showButton: Boolean = false) {
        binding.viewEmpty.apply {
            visibility = View.VISIBLE
            setData(
                data = EmptyView.Data(
                    R.drawable.splash_icon,
                    message,
                    if (showButton) requireContext().getString(R.string.retry) else null
                )
            ) {
                permissionManager.checkPermissions()
            }
        }
    }

    private fun loadImages() {
        checkImageCount()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.galleryData.collectLatest {
                mAdapter?.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvImages.run {
            mAdapter = GalleryAdapter(this@GalleryFragment)
            mAdapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    checkImageCount()
                }
            })
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }

    private fun checkImageCount() {
        if (mAdapter?.itemCount == 0) showEmptyView(getString(R.string.no_images_found))
        else binding.viewEmpty.isVisible = false
    }

    override fun onItemClick(uri: String?) {
        val action = GalleryFragmentDirections.galleryToDetail(uri)
        findNavController().navigate(action)
    }
}