package com.example.napptiluschallenge.mainModel.model

import com.example.napptiluschallenge.common.entities.WorkersEntity

class MainRepository {
    private val remoteDatabase = RemoteDatabase()

    suspend fun getWorkers(): WorkersEntity = remoteDatabase.getWorkers()

    suspend fun getWorkers(id : Int): WorkersEntity = remoteDatabase.getWorker(id)



}