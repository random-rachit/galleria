package com.rachitbhutani.galleria.detail

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.OnScaleGestureListener
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.rachitbhutani.galleria.databinding.FragmentDetailBinding
import com.rachitbhutani.galleria.utils.ScaleDetectorListener

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.ivDetailContent.setImageURI(Uri.parse(args.imageId))
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        setupScaling()
    }

    private fun setupScaling() {
        val scaleGestureDetector =
            ScaleGestureDetector(requireContext(), ScaleDetectorListener(binding.ivDetailContent))
        binding.ivDetailContent.setOnTouchListener { _, event ->
            return@setOnTouchListener scaleGestureDetector.onTouchEvent(event)
        }
    }
}