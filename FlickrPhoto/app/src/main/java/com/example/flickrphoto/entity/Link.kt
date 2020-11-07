package com.example.flickrphoto.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "links",
    primaryKeys = arrayOf("pid", "link"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Point::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("pid"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class Link(
    @ColumnInfo(name = "pid")
    val pid: Int,
    @ColumnInfo(name = "link")
    val link: String
)