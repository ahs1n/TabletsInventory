package com.example.tabletsinventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tabletsinventory.model.Tablets

@Database(entities = [Tablets::class], version = 1, exportSchema = false)
abstract class TabletDatabase : RoomDatabase() {

    abstract fun tabletsDao(): TabletsDao

    companion object {
        @Volatile
        private var INSTANCE: TabletDatabase? = null

        fun getDatabase(context: Context): TabletDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TabletDatabase::class.java,
                    "tablets_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}