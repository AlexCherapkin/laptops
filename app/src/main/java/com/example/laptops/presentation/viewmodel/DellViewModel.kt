package com.example.laptops.presentation.viewmodel

import android.content.Context
import com.example.laptops.R
import com.example.laptops.data.database.DatabaseProvider
import com.example.laptops.domain.network.NetworkService
import com.example.laptops.presentation.fragments.DellFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.IOException

class DellViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState =
        MutableStateFlow<DellFragment.ScreenState>(DellFragment.ScreenState.Loading)
    val screenState: StateFlow<DellFragment.ScreenState> = _screenState
    private val dellDao = DatabaseProvider.provideDatabase(context).laptopsDao()

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.value = DellFragment.ScreenState.Loading
                val dell = try {
                    NetworkService(context).loadDell().also {
                        dellDao.insertAll(it)
                    }
                } catch (ex: IOException){
                    dellDao.getAll()
                }
                _screenState.value = DellFragment.ScreenState.DataLoaded(dell)
            } catch(ex: Throwable) {
                _screenState.value = DellFragment.ScreenState.Error(context.getString(R.string.error))
            }
        }
    }
}