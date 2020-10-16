package com.example.droidcafe

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        findViewById<TextView>(R.id.text_header).text = ("You orderd a "
                + intent.getStringExtra(MainActivity.EXTRA_TYPE) + ".")
        val spinner = findViewById<Spinner>(R.id.phone_type)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.labels_array, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        if (spinner != null) {
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }
    }

    fun displayToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun onRadioButtonClicked(view: View) {
        val check = (view as RadioButton).isChecked
        when (view.id) {
            R.id.sameday -> if (check) displayToast(getString(R.string.delivery_same))
            R.id.nextday -> if (check) displayToast(getString(R.string.delivery_next))
            R.id.pickup -> if (check) displayToast(getString(R.string.delivery_pick))
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val spinnerLabel = parent!!.getItemAtPosition(position).toString()
        displayToast(spinnerLabel)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
