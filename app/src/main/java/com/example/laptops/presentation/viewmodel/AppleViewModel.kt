package com.example.laptops.presentation.viewmodel

import android.content.Context
import com.example.laptops.R
import com.example.laptops.data.database.DatabaseProvider
import com.example.laptops.domain.network.NetworkService
import com.example.laptops.presentation.fragments.AppleFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.IOException

class AppleViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState =
        MutableStateFlow<AppleFragment.ScreenState>(AppleFragment.ScreenState.Loading)
    val screenState: StateFlow<AppleFragment.ScreenState> = _screenState
    private val appleDao = DatabaseProvider.provideDatabase(context).laptopsDao()

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.value = AppleFragment.ScreenState.Loading
                val apple = try {
                    NetworkService(context).loadApple().also {
                        appleDao.insertAll(it)
                    }
                } catch (ex: IOException){
                   appleDao.getAll()
                }
                _screenState.value = AppleFragment.ScreenState.DataLoaded(apple)
            } catch(ex: Throwable) {
                _screenState.value = AppleFragment.ScreenState.Error(context.getString(R.string.error))
            }
        }
    }
}