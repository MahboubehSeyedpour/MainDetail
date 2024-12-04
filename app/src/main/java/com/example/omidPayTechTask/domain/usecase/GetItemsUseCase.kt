package com.example.omidPayTechTask.domain.usecase

import com.example.omidPayTechTask.data.remote.api.NetworkApiState
import com.example.omidPayTechTask.domain.ResourceState
import com.example.omidPayTechTask.domain.dto_to_model_mapper.toItemModel
import com.example.omidPayTechTask.domain.model.ItemModel
import com.example.omidPayTechTask.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repository: Repository) {

    fun getItems(): Flow<ResourceState<List<ItemModel>>> =
        flow<ResourceState<List<ItemModel>>> {
            repository.getItems().collect { result ->
                when (result) {
                    is NetworkApiState.Error -> {
                        emit(
                            ResourceState.Error(
                                title = result.message,
                                errorData = "",
                                state = false
                            )
                        )
                    }

                    is NetworkApiState.Loading -> {
                        // Handle loading state if needed
                    }

                    is NetworkApiState.Success -> {
                        emit(ResourceState.Success(data = result.data?.map { it.toItemModel() }
                            ?: listOf()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
}