package com.example.roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase.models.Employee

@Database(entities = arrayOf(Employee::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        private var INSTANE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANE == null) {
                INSTANE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_data"
                ).allowMainThreadQueries().build()
            }
            return INSTANE!!
        }

        fun deleteInstance() {
            INSTANE = null
        }
    }
}