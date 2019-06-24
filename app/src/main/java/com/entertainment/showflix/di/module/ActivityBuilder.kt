package com.entertainment.showflix.di.module

import com.entertainment.showflix.di.annotations.ActivityScope
import com.entertainment.showflix.feature_album.ui.AlbumsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun bindAlbumActivity(): AlbumsActivity
}
