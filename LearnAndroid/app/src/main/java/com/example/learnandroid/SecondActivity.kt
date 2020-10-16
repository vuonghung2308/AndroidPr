package com.example.learnandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    companion object {
        val EXTRA_REPLY = SecondActivity::class.java.name + ".extra.REPLY";
    }
    lateinit var textView: TextView
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView = findViewById(R.id.text_message)
        editText = findViewById(R.id.editText_reply)
        textView.apply {
            if(!msg.equals(""))
                setText(msg)
            else setText("Has no message!")
        }
    }

    fun actionReply(view: View) {
        var intent = Intent()
        intent.putExtra(EXTRA_REPLY, editText.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}