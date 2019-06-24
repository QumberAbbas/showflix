package com.entertainment.showflix

import com.entertainment.showflix.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ShowflixApplication : DaggerApplication() {

    companion object {
        private lateinit var sInstance: ShowflixApplication
        fun getInstance(): ShowflixApplication {
            return sInstance
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .create(this)
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }
}