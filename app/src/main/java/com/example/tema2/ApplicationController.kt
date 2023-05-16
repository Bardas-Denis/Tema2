package com.example.tema2

import android.app.Application
import androidx.room.Room
import androidx.room.Room.databaseBuilder

class ApplicationController: Application() {
    companion object {
        private var instance: ApplicationController? = null
        private var zooAnimalDatabase: ZooAnimalDatabase? = null
        fun getZooAnimalDatabase():ZooAnimalDatabase?  {
            return zooAnimalDatabase
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        setupDataBase()
    }
    private fun setupDataBase() {
        val databaseName = "ZooAnimalDB"
        zooAnimalDatabase = Room.databaseBuilder(
            applicationContext,
            ZooAnimalDatabase::class.java,
            databaseName
        ).build()
    }



}