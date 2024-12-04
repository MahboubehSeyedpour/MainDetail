package com.example.omidPayTechTask.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omidPayTechTask.domain.model.ItemModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _item =
        MutableStateFlow<ItemModel?>(null)
    val item: StateFlow<ItemModel?> get() = _item.asStateFlow()

    init {
        viewModelScope.launch {

            val item = Gson().fromJson(
                savedStateHandle.get<String>("item")!!,
                ItemModel::class.java
            )

            _item.emit(item)
        }
    }
}