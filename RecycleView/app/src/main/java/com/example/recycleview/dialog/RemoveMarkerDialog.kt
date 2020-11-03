package com.example.recycleview.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import com.example.recycleview.R
import java.lang.ref.WeakReference

class RemoveMarkerDialog(context: Context) : Dialog(context) {

    interface Listenner {
        fun yesAction()
    }

    var listenner: Listenner? = null
        set(value) {
            field = value
        }

    private val buttonNo by lazy { findViewById(R.id.button_no) as Button }
    private val buttonYes by lazy { findViewById(R.id.button_yes) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listennerRef: WeakReference<Listenner> = WeakReference(listenner)
        setContentView(R.layout.remove_marker)
        buttonNo.setOnClickListener { view -> dismiss() }
        buttonYes.setOnClickListener { view -> listennerRef.get()?.yesAction() }
    }

}