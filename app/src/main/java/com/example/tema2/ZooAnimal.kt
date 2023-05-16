package com.example.tema2

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ZooAnimal(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name="animal") val animal: String,
    @ColumnInfo(name="continent") val continent: String
)
{
    fun convert(): ZooAnimalModel
    {
        return  ZooAnimalModel(uid,animal,continent)
    }
}