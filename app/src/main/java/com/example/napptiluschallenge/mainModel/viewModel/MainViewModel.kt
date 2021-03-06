package com.example.napptiluschallenge.mainModel.viewModel

import androidx.lifecycle.*
import com.example.napptiluschallenge.R
import com.example.napptiluschallenge.common.entities.WorkersEntity
import com.example.napptiluschallenge.mainModel.model.MainRepository
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject


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

    suspend fun getWorkersByGender(gender : String) {
        viewModelScope.launch {
            try {
                loaded.value = false
                val resultServer = repository.getWorkers()
                val workers = resultServer.results.filter {
                    it.gender == gender
                }
                resultServer.results = workers
                result.value = resultServer
            } catch (e: Exception) {
                snackbarMsg.value = R.string.server_error
            } finally {
                loaded.value = true
            }
        }
    }

    suspend fun getWorkersByProfession(profession : String) {
        viewModelScope.launch {
            try {
                loaded.value = false
                val resultServer = repository.getWorkers()
                val workers = resultServer.results.filter {
                    it.profession == profession
                }
                resultServer.results = workers
                result.value = resultServer
            } catch (e: Exception) {
                snackbarMsg.value = R.string.server_error
            } finally {
                loaded.value = true
            }
        }
    }

    suspend fun getWorkersByProfessionAndGender(profession : String, gender : String) {
        viewModelScope.launch {
            try {
                loaded.value = false
                val resultServer = repository.getWorkers()
                val workers = resultServer.results.filter {
                    it.profession == profession && it.gender == gender
                }
                resultServer.results = workers
                result.value = resultServer
            } catch (e: Exception) {
                snackbarMsg.value = R.string.server_error
            } finally {
                loaded.value = true
            }
        }
    }
}