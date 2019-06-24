package com.entertainment.showflix.di.components

import com.entertainment.showflix.ShowflixApplication
import com.entertainment.showflix.di.module.ActivityBuilder
import com.entertainment.showflix.di.module.AppModule
import com.entertainment.showflix.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    AndroidSupportInjectionModule::class,
    NetworkModule::class, ActivityBuilder::class])

interface AppComponent : AndroidInjector<ShowflixApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ShowflixApplication>()
}
