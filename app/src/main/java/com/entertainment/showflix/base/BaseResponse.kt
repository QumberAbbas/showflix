package com.entertainment.showflix.base

class BaseResponse<T>(var status: Status = Status.IDLE,
                      var data: T? = null,
                      var message: String? = null) {

    companion object {

        fun <T> success(data: T, message: String? = ""): BaseResponse<T> {
            return BaseResponse(Status.SUCCESS, data, message)
        }

        fun <T> error(msg: String, data: T?): BaseResponse<T> {
            return BaseResponse(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): BaseResponse<T> {
            return BaseResponse(Status.LOADING, data, null)
        }

    }
}

enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}