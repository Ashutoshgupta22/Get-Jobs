package com.aspark.lokalassign.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [],
    version = 1)
abstract class JobsDatabase : RoomDatabase() {
//    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile private var instance: JobsDatabase? = null

        fun getDatabase(context: Context): JobsDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, JobsDatabase::class.java,
                "recipes.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}