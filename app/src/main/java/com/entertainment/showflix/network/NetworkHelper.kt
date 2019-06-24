package com.entertainment.showflix.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.entertainment.showflix.base.BaseResponse
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class NetworkHelper @Inject constructor() {

    var disposable: Disposable? = null

    fun <T> serviceCall(call: Single<Response<T>>): LiveData<BaseResponse<T>> {

        val responseData = MutableLiveData<BaseResponse<T>>()
        responseData.value = BaseResponse.loading(null)

        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Response<T>> {
                    override fun onSuccess(response: Response<T>) {

                        if (response.isSuccessful) {
                            response.body()?.let {
                                responseData.value = BaseResponse.success(it)

                            } ?: run {
                                responseData.value = BaseResponse.error(response.message(), null)
                            }
                        } else {
                            response.errorBody()?.let {
                                responseData.value = BaseResponse.error(response.message(), null)
                            } ?: run {
                                responseData.value = BaseResponse.error("Something went wrong", null)
                            }
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onError(e: Throwable) {
                        e.message?.let {
                            responseData.value = BaseResponse.error(it, null)
                        }
                    }
                })

        return responseData
    }

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed)
                it.dispose()
        }
    }
}
