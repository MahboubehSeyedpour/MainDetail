package com.example.omidPayTechTask.data.remote.api

data class ModelState<T>(
    var isLoading: Boolean = false,
    var response: T? = null,
    var error: String = ""
)