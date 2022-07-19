package com.example.fragmenthomework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


private const val ARG_PARAM_TEXT = "text"

class BlankFragment : Fragment() {

    private lateinit var text: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(ARG_PARAM_TEXT)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.text).text = arguments?.getString(ARG_PARAM_TEXT)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        arguments?.putString(ARG_PARAM_TEXT, text)
        super.onSaveInstanceState(outState)
    }

    fun addText(text: String) {
        this.text = this.text +( if (this.text.isBlank()) "" else "\n" ) + text
        view?.findViewById<TextView>(R.id.text)?.text = this.text
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TEXT, param1)
                }
            }
    }
}