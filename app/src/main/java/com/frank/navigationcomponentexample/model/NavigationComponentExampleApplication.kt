package com.frank.navigationcomponentexample.model

import android.app.Application
import androidx.room.Room

class NavigationComponentExampleApplication:Application() {
    companion object{
        lateinit var database:AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "mi_base_datos"
        )
            .build()
    }
}