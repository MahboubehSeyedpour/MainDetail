package com.example.omidPayTechTask.domain

sealed class ResourceState<T>(
    val data: T? = null,
    val errorData: String = "",
    val title: String? = null,
    val state: Boolean = false,
    val restartApp: Boolean = false
) {
    class Success<T>(data: T) : ResourceState<T>(data)
    class Error<T>(title: String?, errorData: String = "", state: Boolean = false) :
        ResourceState<T>(title = title, errorData = errorData, state = state)
}
