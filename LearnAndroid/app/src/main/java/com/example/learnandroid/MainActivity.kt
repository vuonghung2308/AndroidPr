package com.example.learnandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {
        val LOG_TAG = MainActivity::class.simpleName;
        val EXTRA_MESSAGE = MainActivity::class.qualifiedName + ".extra.MESSAGE"
        var TEXT_REQUEST = 1
    }

    lateinit var editText: EditText
    lateinit var textView: TextView
    lateinit var textHeader: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.text_message)
        textView = findViewById(R.id.text_reply)
        textHeader = findViewById(R.id.text_header_reply)
        if(savedInstanceState != null) {
            if(savedInstanceState.getBoolean("reply_visible")) {
                textView.setText(savedInstanceState.getString("reply_text"))
                textView.visibility = View.VISIBLE
                textHeader.visibility = View.VISIBLE
            }
        }
    }

    fun launchSecondActivity(view: View) {
        Log.d(LOG_TAG, "lauchSecondActivity")
        var intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_MESSAGE, editText.text.toString())
        startActivityForResult(intent, TEXT_REQUEST)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(textHeader.visibility == View.VISIBLE) {
            outState.putString("reply_text", textView.text.toString())
            outState.putBoolean("reply_visible", true)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                textView.apply {
                    setText(data!!.getStringExtra(SecondActivity.EXTRA_REPLY))
                    visibility = View.VISIBLE
                }
                textHeader.visibility = View.VISIBLE
            }
        }
    }
}