package com.budi.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Sound::class], version = 1)
//@TypeConverters(Converters::class)
abstract class SoundDatabase : RoomDatabase() {

    abstract fun soundDao(): SoundDao

    companion object {
        private var INSTANCE: SoundDatabase? = null

        fun getInstance(context: Context): SoundDatabase? {
            if (INSTANCE == null) {
                synchronized(SoundDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            SoundDatabase::class.java, "database-name")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}