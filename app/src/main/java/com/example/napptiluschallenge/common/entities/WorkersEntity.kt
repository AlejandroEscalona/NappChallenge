package com.example.napptiluschallenge.common.entities

data class WorkersEntity(val current : Int,
                         val total : Int,
                         val results : List<Worker>)
