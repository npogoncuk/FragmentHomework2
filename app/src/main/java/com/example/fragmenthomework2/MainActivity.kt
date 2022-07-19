package com.example.fragmenthomework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), AlertDialogFragment.OnOkClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, BlankFragment.newInstance(""))
                .commit()
            showAlertDialog()
        }

    }

    private fun showAlertDialog() {
        val alertDialogFragment = AlertDialogFragment()
        alertDialogFragment.show(supportFragmentManager, "ALERT_DIALOG_FRAGMENT")
    }

    override fun onOkClicked(text: String) {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as BlankFragment).addText(text)
    }
}