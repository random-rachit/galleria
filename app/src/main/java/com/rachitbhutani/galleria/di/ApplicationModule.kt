package com.rachitbhutani.galleria.di

import android.app.Activity
import android.app.Application
import android.content.ContentResolver
import com.rachitbhutani.galleria.gallery.GalleryAdapterListener
import com.rachitbhutani.galleria.gallery.GalleryFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideContentResolver(application: Application): ContentResolver =
        application.contentResolver

}