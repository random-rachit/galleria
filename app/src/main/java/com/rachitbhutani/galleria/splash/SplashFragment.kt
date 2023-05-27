package com.rachitbhutani.galleria.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.rachitbhutani.galleria.R
import com.rachitbhutani.galleria.databinding.FragmentSplashBinding
import com.rachitbhutani.galleria.utils.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var permissionManager: PermissionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionManager.setActions(grantAction = ::proceedNav, declineAction = ::proceedNav)
        permissionManager.checkPermissions()
    }

    private fun proceedNav() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            findNavController().navigate(
                SplashFragmentDirections.splashToGallery(), NavOptions.Builder()
                    .setPopUpTo(
                        R.id.splash_fragment,
                        true
                    ).build()
            )
        }
    }
}