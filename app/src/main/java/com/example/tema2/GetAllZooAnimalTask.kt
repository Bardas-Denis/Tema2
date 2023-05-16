package com.example.tema2

import android.os.AsyncTask

class GetAllZooAnimalTask(private val zooAnimalDatabase: ZooAnimalDatabase?,private val listener: ZooAnimalRepository.OnGetListener):
    AsyncTask<Void, Void, List<ZooAnimal>>() {
    override fun doInBackground(vararg p0: Void?): List<ZooAnimal>? {
        return zooAnimalDatabase?.zooAnimalDao()?.getAll()
    }

    override fun onPostExecute(animals: List<ZooAnimal>) {
        super.onPostExecute(animals)
        listener.onSuccess(animals)
    }
}