package com.entertainment.showflix.feature_album.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface AlbumDAO {

    @Query("SELECT * FROM albums ORDER BY title ASC")
    fun getAll(): LiveData<List<Album>>

    @Query("SELECT * FROM albums GROUP BY albumId ORDER BY title ASC")
    fun getAllGroupByAlbumId(): LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<Album>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg album: Album)

    @Delete
    fun delete(vararg album: Album)

    @Update
    fun update(vararg album: Album)

    @Query("DELETE FROM albums")
    fun deleteAll()
}