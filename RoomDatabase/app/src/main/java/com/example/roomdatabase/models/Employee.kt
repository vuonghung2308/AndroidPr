package com.example.roomdatabase.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "designation")
    val designation: String
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var eid: Int = 0

    override fun toString(): String {
        return "id: " + eid + ", name: " + name + ", designation: " + designation
    }
}