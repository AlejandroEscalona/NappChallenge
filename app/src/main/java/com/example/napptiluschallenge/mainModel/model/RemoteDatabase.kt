package com.example.napptiluschallenge.mainModel.model

import com.example.napptiluschallenge.common.dataAccess.WorkerService
import com.example.napptiluschallenge.common.entities.WorkersEntity
import com.example.napptiluschallenge.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RemoteDatabase {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(WorkerService::class.java)

    suspend fun getWorkers () : WorkersEntity = withContext(Dispatchers.IO){
        service.getAllWorker()
    }
}