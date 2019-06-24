package com.entertainment.showflix.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.entertainment.showflix.di.ViewModelFactory
import com.entertainment.showflix.di.annotations.ViewModelKey
import com.entertainment.showflix.feature_album.viewmodel.AlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    internal abstract fun mainViewModel(viewModel: AlbumsViewModel): ViewModel

}