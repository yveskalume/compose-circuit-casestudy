package com.yveskalume.circuitcasestudy.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface FruitDao {

    @Insert
    fun insert(fruit: Fruit)

    @Query("SELECT * FROM fruit")
    fun getAll() : Flow<List<Fruit>>

    @Delete
    fun delete(fruit: Fruit)
}