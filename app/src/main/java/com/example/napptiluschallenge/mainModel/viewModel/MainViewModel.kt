package com.example.napptiluschallenge.mainModel.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.common.entities.WorkersEntity
import com.example.napptiluschallenge.mainModel.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    private val result = MutableLiveData<WorkersEntity>()
    fun getResult(): LiveData<WorkersEntity> = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    private val loaded = MutableLiveData<Boolean>()
    fun isLoaded() = loaded

    suspend fun getWorkers(){
        viewModelScope.launch {
            try {
                loaded.value = false
                val resultServer = repository.getWorkers()
                result.value = resultServer
            }catch (e : Exception){
                snackbarMsg.value = R.string.server_error
            }finally {
                loaded.value = true
            }
        }
    }
}