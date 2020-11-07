package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.roomdatabase.database.AppDatabase
import com.example.roomdatabase.models.Employee

class MainActivity : AppCompatActivity() {
    private lateinit var saveButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var designationEditText: EditText
    private lateinit var listTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
    }

    fun initComponent() {
        saveButton = findViewById(R.id.save_button)
        nameEditText = findViewById(R.id.name_edittext)
        designationEditText = findViewById(R.id.designation_edittext)
        listTextView = findViewById(R.id.list_textview)
        showList()
    }

    fun showList() {
        val list = AppDatabase.getInstance(applicationContext).employeeDao().getAllEmployee()
        var string = ""
        for (employee in list) {
            string += employee.toString() + "\n"
        }
        listTextView.text = string
    }

    fun deleteAction(view: View) {
        AppDatabase.getInstance(applicationContext).employeeDao().dellAllEmployee()
        showList()
    }

    fun saveAction(view: View) {
        val name = nameEditText.text.toString()
        val designation = designationEditText.text.toString()
        val e = Employee(name, designation)
        AppDatabase.getInstance(applicationContext).employeeDao().insertEmployee(e)
        showList()
    }
}