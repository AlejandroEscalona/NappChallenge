package com.example.napptiluschallenge.common.dataAccess

import com.example.napptiluschallenge.common.entities.WorkersEntity
import com.example.napptiluschallenge.common.utils.Constants
import retrofit2.http.GET

interface WorkerService {
    @GET(Constants.BASE_URL+Constants.ALL_WORKER)
    suspend fun getAllWorker() : WorkersEntity

    @GET(Constants.BASE_URL+Constants.ALL_WORKER)
    suspend fun getWorker(id  : Int) : WorkersEntity
}