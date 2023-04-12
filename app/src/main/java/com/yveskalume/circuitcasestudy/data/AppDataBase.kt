package com.yveskalume.circuitcasestudy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fruit::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fruitDao(): FruitDao

    @Volatile
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            val dbInstance = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "cicuit"
            ).build()

            instance = dbInstance
            return instance as AppDatabase
        }
    }
}