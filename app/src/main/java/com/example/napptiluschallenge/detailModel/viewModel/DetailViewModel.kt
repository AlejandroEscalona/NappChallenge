package com.example.napptiluschallenge.detailModel.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.common.entities.Worker
import com.example.napptiluschallenge.detailModel.model.DetailRepository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel(){

    private val repository = DetailRepository()

    private val worker = MutableLiveData<Worker>()
    fun getResult(): LiveData<Worker> = worker

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    suspend fun getWorker(id : Int){
        viewModelScope.launch {
            try {
                val resultServer = repository.getWorker(id)
                worker.value = resultServer
            }catch (e : Exception){
                snackbarMsg.value = R.string.server_error
            }
        }
    }





}