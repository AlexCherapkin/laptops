package com.example.laptops.presentation.viewmodel

import android.content.Context
import com.example.laptops.R
import com.example.laptops.domain.network.NetworkService
import com.example.laptops.presentation.fragments.AppleFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class AppleViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState =
        MutableStateFlow<AppleFragment.ScreenState>(AppleFragment.ScreenState.Loading)
    val screenState: StateFlow<AppleFragment.ScreenState> = _screenState

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.emit(AppleFragment.ScreenState.Loading)
                val apple = NetworkService.loadApple()
                _screenState.emit(AppleFragment.ScreenState.DataLoaded(apple))
            } catch (ex: Throwable) {
                _screenState.emit(AppleFragment.ScreenState.Error(context.resources.getString(R.string.error)))
            }
        }
    }
}