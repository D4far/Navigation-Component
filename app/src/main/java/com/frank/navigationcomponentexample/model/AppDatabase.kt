package com.frank.navigationcomponentexample.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EstudianteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun estudianteDao(): EstudianteDAO
}