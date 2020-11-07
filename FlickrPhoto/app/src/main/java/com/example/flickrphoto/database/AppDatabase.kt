package com.example.flickrphoto.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.flickrphoto.entity.Link
import com.example.flickrphoto.entity.Point

@Database(entities = arrayOf(Point::class, Link::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pointDao(): PointDao
    abstract fun linkDao(): LinkDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_data"
                ).allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}