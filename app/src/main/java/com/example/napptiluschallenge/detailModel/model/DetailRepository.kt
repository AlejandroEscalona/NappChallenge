package com.example.napptiluschallenge.detailModel.model

import com.example.napptiluschallenge.common.entities.Worker
import com.example.napptiluschallenge.common.dataAccess.RemoteDatabase

class DetailRepository {
    private val remoteDatabase = RemoteDatabase()

    suspend fun getWorker(id : Int): Worker = remoteDatabase.getWorker(id)
}