package com.example.tema2

data class ZooAnimalModel (val id: Int,val animal:String,val continent:String)
{
    fun convert(): ZooAnimal
    {
        return  ZooAnimal(id,animal,continent)
    }
}