package com.entertainment.showflix.feature_album.viewmodel

import android.arch.lifecycle.LiveData
import com.entertainment.showflix.base.BaseResponse
import com.entertainment.showflix.base.BaseViewModel
import com.entertainment.showflix.feature_album.model.Album
import com.entertainment.showflix.feature_album.repository.AlbumRepo
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(private val repo: AlbumRepo) : BaseViewModel() {
    private lateinit var albums: LiveData<BaseResponse<List<Album>>>

    fun getAlbums(): LiveData<BaseResponse<List<Album>>> {

        if (!::albums.isInitialized) {
            albums = repo.getLiveAlbums()
        }
        return albums
    }

}