package com.example.omidPayTechTask.domain.repository

import com.example.omidPayTechTask.data.remote.api.NetworkApiState
import com.example.omidPayTechTask.data.remote.model.ItemDTO
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getItems(): Flow<NetworkApiState<List<ItemDTO>>>
}