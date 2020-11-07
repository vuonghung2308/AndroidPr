package com.example.flickrphoto

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button

class RemoveDialog(context: Context) : Dialog(context) {
    interface Listenner {
        fun yesAction()
    }

    private var listenner: Listenner? = null
    fun setListenner(listenner: Listenner) {
        this.listenner = listenner
    }

    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remove_dialog)
        initComponent()
    }

    private fun initComponent() {
        yesButton = findViewById(R.id.button_yes)
        noButton = findViewById(R.id.button_no)
        noButton.setOnClickListener { view -> dismiss() }
        yesButton.setOnClickListener { view -> listenner?.yesAction() }
    }
}