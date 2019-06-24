package com.entertainment.showflix.gateway

import com.entertainment.showflix.feature_album.model.Album
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET


interface ServiceGateway {

    @GET("photos")
    fun getAlbums(): Single<Response<List<Album>>>
}
