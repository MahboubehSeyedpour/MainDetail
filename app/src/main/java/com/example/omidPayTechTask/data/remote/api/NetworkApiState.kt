package com.example.omidPayTechTask.data.remote.api

sealed class NetworkApiState<T>(
    val data: T? = null,
    val message: String = "",
    val timeStamp: String? = null,
    val title: String? = null,
    val responseCode: Int? = null,
) {
    class Success<T>(data: T) : NetworkApiState<T>(data)
    class Error<T>(title: String?, message: String = "", timeStamp: String? = null, responseCode: Int? = null) :
        NetworkApiState<T>(title = title, message = message, timeStamp = timeStamp, responseCode = responseCode)

    class Loading<T>(data: T? = null) : NetworkApiState<T>(data)
}