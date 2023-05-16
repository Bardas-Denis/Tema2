package com.example.tema2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZooAnimalDao {
    @Query("SELECT * FROM zooAnimal")
    fun getAll(): List<ZooAnimal>?

    @Insert
    fun insert(animal: ZooAnimal?)

    @Delete
    fun delete(animal: ZooAnimal?)
}