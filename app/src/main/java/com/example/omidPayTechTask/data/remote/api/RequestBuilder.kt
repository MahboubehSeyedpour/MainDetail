package com.example.omidPayTechTask.data.remote.api

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class RequestBuilder {

    fun <T : Any> flowRequestBuilder(call: suspend () -> Response<T>): Flow<NetworkApiState<T>> {
        return flow {
            emit(NetworkApiState.Loading())
            try {
                val response = call()
                if (response.isSuccessful) {
                    emit(NetworkApiState.Success(response.body()!!))
                } else {
                    response.errorBody()?.let { errorBody ->
                        val errorResponse = parseErrorResponse(errorBody.string())
                        emit(
                            NetworkApiState.Error(
                                message = errorResponse?.message ?: ((response.body()?.toString() ?: "").plus(", error code: ${response.code()}")),
                                title = errorResponse?.title,
                                timeStamp = errorResponse?.timestamp,
                                responseCode = response.code()
                            )
                        )
                    }
                }
            } catch (ex: NoInternetException) {
                emit(
                    NetworkApiState.Error(
                        message = ex.message!!,
                        title = "",
                        timeStamp = ""
                    )
                )
            } catch (ex: HttpException) {
                emit(
                    NetworkApiState.Error(
                        message = ex.localizedMessage ?: "An unexpected error occurred",
                        title = "",
                        timeStamp = ""
                    )
                )
            } catch (ex: IOException) {
                emit(
                    NetworkApiState.Error(
                        message = "please check your network connection",
                        title = "",
                        timeStamp = ""
                    )
                )
            }!!
        }
    }
}

fun parseErrorResponse(responseBody: String?): ErrorResponse? {
    return try {
        Gson().fromJson(responseBody, ErrorResponse::class.java)
    } catch (e: Exception) {
        null
    }
}