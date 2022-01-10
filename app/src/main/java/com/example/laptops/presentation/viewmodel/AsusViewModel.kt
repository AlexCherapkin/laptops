package com.example.laptops.presentation.viewmodel

import android.content.Context
import com.example.laptops.R
import com.example.laptops.data.database.DatabaseProvider
import com.example.laptops.domain.network.NetworkService
import com.example.laptops.presentation.fragments.AsusFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.IOException

class AsusViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState =
        MutableStateFlow<AsusFragment.ScreenState>(AsusFragment.ScreenState.Loading)
    val screenState: StateFlow<AsusFragment.ScreenState> = _screenState
    private val asusDao = DatabaseProvider.provideDatabase(context).laptopsDao()

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.value = AsusFragment.ScreenState.Loading
                val asus = try {
                    NetworkService(context).loadAsus().also {
                        asusDao.insertAll(it)
                    }
                } catch (ex: IOException){
                    asusDao.getAll()
                }
                _screenState.value = AsusFragment.ScreenState.DataLoaded(asus)
            } catch(ex: Throwable) {
                _screenState.value = AsusFragment.ScreenState.Error(context.getString(R.string.error))
            }
        }
    }
}