package com.yveskalume.circuitcasestudy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fruit(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
)
