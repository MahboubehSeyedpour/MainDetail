package com.example.omidPayTechTask.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omidPayTechTask.data.remote.model.ItemDTO
import com.example.omidPayTechTask.domain.ResourceState
import com.example.omidPayTechTask.domain.usecase.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getItemsUseCase: GetItemsUseCase): ViewModel() {

    var searchbarQuery: String by mutableStateOf("")
        private set

    private val _items = MutableStateFlow<List<ItemDTO>>(listOf())
    val items: StateFlow<List<ItemDTO>> get() = _items.asStateFlow()



    init {

        viewModelScope.launch {
            getItemsUseCase.getItems().collect { result ->
                when(result) {
                    is ResourceState.Error -> {}
                    is ResourceState.Success -> {
                        _items.emit(result.data ?: listOf())
                    }
                }
            }
        }

    }


    fun onSearchQueryChanged(newQuery: String) {
        searchbarQuery = newQuery
    }
}