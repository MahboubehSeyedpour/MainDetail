package com.example.omidPayTechTask.data.repositoryImpl

import com.example.omidPayTechTask.data.remote.api.ApiService
import com.example.omidPayTechTask.data.remote.api.NetworkApiState
import com.example.omidPayTechTask.data.remote.api.RequestBuilder
import com.example.omidPayTechTask.data.remote.model.ItemDTO
import com.example.omidPayTechTask.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiService) : Repository,
    RequestBuilder() {

    override fun getItems(): Flow<NetworkApiState<List<ItemDTO>>> =
        flowRequestBuilder { api.getItems() }

}