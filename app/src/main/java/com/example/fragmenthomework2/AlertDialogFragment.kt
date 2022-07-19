package com.example.fragmenthomework2

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment


private const val TEXT_KEY = "text_key"

class AlertDialogFragment : DialogFragment() {
    private lateinit var myDialog: Dialog
    private lateinit var onOkClickedImplementation: OnOkClicked
    private lateinit var myInput: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        onOkClickedImplementation = requireActivity() as OnOkClicked

        val input = EditText(requireContext())
        input.setText(savedInstanceState?.getString(TEXT_KEY) ?: "")

        myInput = input
        val filter =
            InputFilter { source, start, end, _, _, _ ->
                for (i in start until end) {
                    if (!Character.isLetterOrDigit(source[i])) {
                        return@InputFilter ""
                    }
                }
                null
            }
        input.filters += filter


        val dialog = AlertDialog.Builder(requireContext())
        with(dialog) {
            setTitle("Create a list")
            setMessage("Please enter the message")
            setView(input)
            setPositiveButton(
                "OK"
            ) { _, _ -> }
            setNegativeButton(
                "Cancel"
            ) { _, _ -> dismiss() }

        }
        myDialog = dialog.create()
        isCancelable = false
        return myDialog
    }

    override fun onStart() {
        super.onStart()

        val positiveButton: Button =
            (myDialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE) as Button
        positiveButton.setOnClickListener {
            onOkClickedImplementation.onOkClicked(myInput.text.toString())
            myInput.text.clear()
        }
        positiveButton.isEnabled = myInput.text.toString().length > 3
        myInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val string = p0.toString()
                positiveButton.isEnabled = string.length > 3
            }

        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(TEXT_KEY, myInput.text.toString())
        super.onSaveInstanceState(outState)
    }

    interface OnOkClicked {
        fun onOkClicked(text: String)
    }

}