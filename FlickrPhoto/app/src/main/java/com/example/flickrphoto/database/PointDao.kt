package com.example.flickrphoto.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.flickrphoto.entity.Point

@Dao
interface PointDao {
    @Insert
    fun insertPoint(vararg points: Point)

    @Query("SELECT * FROM points WHERE lat = :lat AND lon = :lon")
    fun getPoint(lat: Double, lon: Double): Point

    @Query("SELECT address FROM points WHERE lat = :lat AND lon = :lon")
    fun getAddress(lat: Double, lon: Double): String

    @Query("SELECT * FROM points")
    fun getAllPoint(): List<Point>

    @Query("DELETE FROM points WHERE lat = :lat AND lon = :lon")
    fun deletePoint(lat: Double, lon: Double)

}