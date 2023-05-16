package com.example.tema2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ZooAnimal::class],version=1, exportSchema = true)
abstract class ZooAnimalDatabase: RoomDatabase() {
    abstract fun zooAnimalDao(): ZooAnimalDao
}