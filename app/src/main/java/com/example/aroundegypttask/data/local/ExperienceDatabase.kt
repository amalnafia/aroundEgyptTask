package com.example.aroundegypttask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aroundegypttask.domain.model.Experience

@Database(entities = [Experience::class], version = 1)
abstract class ExperienceDatabase : RoomDatabase() {

    abstract fun databaseDao(): DataBaseDao

    companion object {
        private var instance: ExperienceDatabase? = null

        fun getInstance(context: Context): ExperienceDatabase? {
            if (instance == null) {
                synchronized(ExperienceDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExperienceDatabase::class.java,
                        "Experience_database"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return instance;
        }
    }
}