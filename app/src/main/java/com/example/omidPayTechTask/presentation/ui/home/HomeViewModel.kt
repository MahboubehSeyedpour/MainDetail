package com.example.omidPayTechTask.presentation.ui.home

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omidPayTechTask.data.remote.model.ItemDTO
import com.example.omidPayTechTask.domain.ResourceState
import com.example.omidPayTechTask.domain.model.ItemModel
import com.example.omidPayTechTask.domain.usecase.GetItemsUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getItemsUseCase: GetItemsUseCase): ViewModel() {

    private val _events = MutableSharedFlow<HomeEvents>()
    val events = _events.asSharedFlow()

    var searchbarQuery: String by mutableStateOf("")
        private set

    private val _items = MutableStateFlow<List<ItemModel>>(listOf())
    val items: StateFlow<List<ItemModel>> get() = _items.asStateFlow()



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

    fun onItemClicked(id: Int) = viewModelScope.launch {
        val item = _items.value.firstOrNull { it.id == id }
        item?.let {
            val jsonString = Uri.encode(Gson().toJson(it))
            _events.emit(HomeEvents.NavigateToDetailScreen(jsonString))
        }
    }
}