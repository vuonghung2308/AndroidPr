package com.example.tictactoe.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.R

class GameEndDialog : DialogFragment() {
    lateinit var textView: TextView
    lateinit var activity: MainActivity
    lateinit var name: String
    lateinit var rootView: View

    companion object {
        fun newInstance(activity: MainActivity, name: String): GameEndDialog {
            val dialog = GameEndDialog()
            dialog.activity = activity
            dialog.name = name
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initViews()
        val dialog = AlertDialog.Builder(context)
            .setView(rootView)
            .setTitle("Winner is:")
            .setCancelable(false)
            .setPositiveButton("Done", null)
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnShowListener { setOnDoneClicked(dialog) }
        return dialog
    }

    fun initViews() {
        rootView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_game_end, null, false)
        textView = rootView.findViewById(R.id.text_view_winner_name)
        textView.text = name
    }

    fun setOnDoneClicked(dialog: AlertDialog) {
        val button: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        button.setOnClickListener { view ->
            onDoneClicked()
        }
    }

    fun onDoneClicked() {
        activity.promptPlayer()
        dismiss()
    }
}