package com.example.roomdatabase.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdatabase.models.Employee

@Dao
interface EmployeeDao {
    @Query("SELECT id, name, designation FROM employee")
    fun getAllEmployee(): List<Employee>

    @Insert
    fun insertEmployee(vararg employees: Employee)

    @Query("DELETE FROM employee")
    fun dellAllEmployee()

    @Delete
    fun deleteEmployee(employee: Employee)
}