package com.yveskalume.circuitcasestudy.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FruitDao {

    @Insert
    suspend fun insert(fruit: Fruit)

    @Query("SELECT * FROM fruit")
    fun getAll() : Flow<List<Fruit>>

    @Delete
    fun delete(fruit: Fruit)
}