package com.example.tictactoe.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class GameBeginDialog : DialogFragment() {

    lateinit var inputLayout1: TextInputLayout
    lateinit var inputLayout2: TextInputLayout
    lateinit var editText1: TextInputEditText
    lateinit var editText2: TextInputEditText
    lateinit var rootView: View
    lateinit var activity: MainActivity
    var player1: String = ""
    var player2: String = ""

    companion object {
        fun newInstance(activity: MainActivity): GameBeginDialog {
            val dialog = GameBeginDialog()
            dialog.activity = activity
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initViews()
        val dialog = AlertDialog.Builder(context)
            .setView(rootView)
            .setTitle("New Game")
            .setCancelable(false)
            .setPositiveButton("Done", null)
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnShowListener { setOnDoneClicked(dialog) }
        return dialog
    }

    fun initViews() {
        rootView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_game_begin, null, false)
        inputLayout1 = rootView.findViewById(R.id.input_layout_1)
        inputLayout2 = rootView.findViewById(R.id.input_layout_2)
        editText1 = rootView.findViewById(R.id.text_view_name_1)
        editText2 = rootView.findViewById(R.id.text_view_name_2)
        addTextWatcher()
    }

    fun setOnDoneClicked(dialog: AlertDialog) {
        val button: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        button.setOnClickListener { view ->
            onDoneClicked()
        }
    }

    fun onDoneClicked() {
        if (isValidName(inputLayout1, player1) && isValidName(inputLayout2, player2)) {
            activity.onPlayerSet(player1, player2)
            dismiss()
        }
    }

    fun isValidName(inputLayout: TextInputLayout, name: String): Boolean {
        if (name.isEmpty()) {
            inputLayout.error = "name can not be empty"
            inputLayout.isErrorEnabled = true
            return false
        }
        if (player1.equals(player2, true)) {
            if (editText1.isFocused) {
                inputLayout1.error = "two name are same"
                inputLayout1.isErrorEnabled = true
            } else {
                inputLayout2.error = "two name are same"
                inputLayout2.isErrorEnabled = true
            }
            return false
        }
        inputLayout.error = ""
        inputLayout.isErrorEnabled = false
        return true

    }

    fun addTextWatcher() {
        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                inputLayout1.error = ""
                inputLayout1.isErrorEnabled = false
                player1 = s.toString()
            }
        })
        editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                inputLayout2.error = ""
                inputLayout2.isErrorEnabled = false
                player2 = s.toString()
            }
        })
    }
}