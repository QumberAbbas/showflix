package com.entertainment.showflix.feature_album.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.entertainment.showflix.base.BaseResponse
import com.entertainment.showflix.feature_album.model.Album
import com.entertainment.showflix.feature_album.model.AlbumDAO
import com.entertainment.showflix.gateway.ServiceGateway
import com.entertainment.showflix.network.NetworkHelper
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepo @Inject constructor(
    private val serviceGateway: ServiceGateway,
    private val dao: AlbumDAO,
    private val networkHelper: NetworkHelper
) {

    private val liveAlbum = MediatorLiveData<BaseResponse<List<Album>>>()

    fun getLiveAlbums(): LiveData<BaseResponse<List<Album>>> {

        val dbSource = dao.getAllGroupByAlbumId()
        liveAlbum.addSource(dbSource) { albums ->
            if (!albums.isNullOrEmpty()) {
                liveAlbum.value = BaseResponse.success(albums)
            } else {
                fetchFromNetwork()
            }
        }
        return liveAlbum
    }

    private fun fetchFromNetwork() {

        val networkSource = networkHelper.serviceCall(serviceGateway.getAlbums())

        liveAlbum.addSource(networkSource) { response ->
            response?.data?.let {
                liveAlbum.removeSource(networkSource)
                Completable.fromCallable { dao.insert(*it.toTypedArray()) }
                    .subscribeOn(Schedulers.newThread())
                    .subscribe()
            } ?: kotlin.run {
                liveAlbum.value = response
            }

        }
    }
}