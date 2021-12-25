package com.example.shaditestapp.usecase.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ShadiUser::class), version = 3, exportSchema = false)
     abstract class ShadiUserDatabase : RoomDatabase() {

        abstract fun wordDao(): ShadiUserDao

        companion object {
            @Volatile
            private var INSTANCE: ShadiUserDatabase? = null

            fun getDatabase(context: Context): ShadiUserDatabase {

                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShadiUserDatabase::class.java,
                        "word_database"
                    )
                        .build()
                    INSTANCE = instance

                    instance
                }
            }
        }
    }
