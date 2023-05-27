package com.rachitbhutani.galleria.utils

import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import javax.inject.Inject

class PermissionManager @Inject constructor(private val fragment: Fragment) {

    private var grantedAction: () -> Unit = {}
    private var declineAction: () -> Unit = {}

    private val requiredPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        android.Manifest.permission.READ_MEDIA_IMAGES
    } else {
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    }

    private var requestPermissionLauncher: ActivityResultLauncher<String> =
        fragment.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(
                    fragment.requireContext(),
                    "Permission Granted",
                    Toast.LENGTH_SHORT
                )
                    .show()
                grantedAction.invoke()
            } else {
                Toast.makeText(
                    fragment.requireContext(),
                    "Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
                declineAction.invoke()
            }
        }

    private fun getPermission(requiredPermission: String) {
        requestPermissionLauncher.launch(requiredPermission)
    }

    fun setActions(grantAction: () -> Unit, declineAction: () -> Unit) {
        grantedAction = grantAction
        this.declineAction = declineAction
    }

    fun checkPermissions(): Boolean {
        return if (hasPermission()) {
            grantedAction.invoke()
            true
        } else {
            getPermission(requiredPermission)
            false
        }
    }

    fun hasPermission(): Boolean = ContextCompat.checkSelfPermission(
        fragment.requireContext(),
        requiredPermission
    ) == PackageManager.PERMISSION_GRANTED


}