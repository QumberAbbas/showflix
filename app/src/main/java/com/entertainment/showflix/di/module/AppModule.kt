package com.entertainment.showflix.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.entertainment.showflix.ShowflixApplication
import com.entertainment.showflix.feature_album.model.AlbumDAO
import com.entertainment.showflix.persistence.LocalDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: ShowflixApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApplication(application: ShowflixApplication): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }


    @Singleton
    @Provides
    fun provideDb(application: Application): LocalDB {
        return Room.databaseBuilder(application, LocalDB::class.java, "dnow-db").build()

    }

    @Singleton
    @Provides
    fun provideMoviesDao(database: LocalDB): AlbumDAO {
        return database.getAlbumDao()
    }

}
