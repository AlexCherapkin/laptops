package com.example.laptops.presentation.viewmodel

import android.content.Context
import com.example.laptops.R
import com.example.laptops.domain.network.NetworkService
import com.example.laptops.presentation.fragments.DellFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class DellViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState =
        MutableStateFlow<DellFragment.ScreenState>(DellFragment.ScreenState.Loading)
    val screenState: StateFlow<DellFragment.ScreenState> = _screenState

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.emit(DellFragment.ScreenState.Loading)
                val dell = NetworkService.loadDell()
                _screenState.emit(DellFragment.ScreenState.DataLoaded(dell))
            } catch (ex: Throwable) {
                _screenState.emit(DellFragment.ScreenState.Error(context.resources.getString(R.string.error)))
            }
        }
    }
}