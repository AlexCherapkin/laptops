package com.example.laptops.presentation.viewmodel

import android.content.Context
import com.example.laptops.R
import com.example.laptops.domain.network.NetworkService
import com.example.laptops.presentation.fragments.AsusFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class AsusViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState =
        MutableStateFlow<AsusFragment.ScreenState>(AsusFragment.ScreenState.Loading)
    val screenState: StateFlow<AsusFragment.ScreenState> = _screenState

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.emit(AsusFragment.ScreenState.Loading)
                val asus = NetworkService.loadAsus()
                _screenState.emit(AsusFragment.ScreenState.DataLoaded(asus))
            } catch (ex: Throwable) {
                _screenState.emit(AsusFragment.ScreenState.Error(context.resources.getString(R.string.error)))
            }
        }
    }
}