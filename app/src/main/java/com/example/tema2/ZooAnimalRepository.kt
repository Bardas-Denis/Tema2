package com.example.tema2

import android.util.Log

class ZooAnimalRepository() {
    interface OnSuccessListener {
        fun onSuccess()
    }
    interface OnGetListener {
        fun onSuccess(items: List<ZooAnimal>)
    }
    private var zooAnimalDatabase: ZooAnimalDatabase?=null

    init {
        zooAnimalDatabase=ApplicationController.getZooAnimalDatabase()
    }
    fun insertZooAnimal(zooAnimal: ZooAnimal,listener: OnSuccessListener)
    {
        InsertZooAnimalTask(zooAnimalDatabase,listener).execute(zooAnimal)
    }
    fun deleteZooAnimal(zooAnimal: ZooAnimal,listener: OnSuccessListener)
    {
        DeleteZooAnimalTask(zooAnimalDatabase,listener).execute(zooAnimal)
    }
    fun getAllAnimals(listener: OnGetListener)
    {
        GetAllZooAnimalTask(zooAnimalDatabase,listener).execute()
    }
}