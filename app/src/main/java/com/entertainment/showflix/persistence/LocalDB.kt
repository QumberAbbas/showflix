package com.entertainment.showflix.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.entertainment.showflix.feature_album.model.Album
import com.entertainment.showflix.feature_album.model.AlbumDAO

@Database(entities = [Album::class], version = 1, exportSchema = false)
abstract class LocalDB:RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDAO
}