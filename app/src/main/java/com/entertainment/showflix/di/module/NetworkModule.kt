package com.entertainment.showflix.di.module

import com.entertainment.showflix.gateway.ServiceGateway
import com.entertainment.showflix.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Named("BASE_URL")
    @Provides
    fun getBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().setLenient().create()


    @Provides
    @Singleton
    fun provideRetroFit(gson: Gson,
                        @Named("BASE_URL") baseUrl: String)
            : Retrofit {

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @Singleton
    fun provideGateway(retrofit: Retrofit): ServiceGateway {
       return retrofit.create(ServiceGateway::class.java)
    }
}
