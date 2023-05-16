package com.example.tema2

import android.os.AsyncTask

class DeleteZooAnimalTask(private val zooAnimalDatabase: ZooAnimalDatabase?, private val listener: ZooAnimalRepository.OnSuccessListener) : AsyncTask<ZooAnimal, Void, Void>() {
    override fun doInBackground(vararg animals: ZooAnimal?): Void? {
        zooAnimalDatabase?.zooAnimalDao()?.delete(animals[0])
        return null
    }
    override fun onPostExecute(aVoid: Void?) {
        super.onPostExecute(aVoid)
        listener.onSuccess()
    }
}