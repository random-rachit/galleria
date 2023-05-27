package com.rachitbhutani.galleria.utils

import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View

class ScaleDetectorListener(private val view: View) : SimpleOnScaleGestureListener() {

    private var scaleFactor = 1.0f

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        scaleFactor *= detector.scaleFactor
        view.scaleX = scaleFactor
        view.scaleY = scaleFactor
        return super.onScale(detector)
    }
}