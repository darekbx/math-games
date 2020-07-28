package com.darekbx.mathgames.ui.equations

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.darekbx.mathgames.R
import kotlinx.android.synthetic.main.fragment_equations.*

class EquationsFragment : Fragment(R.layout.fragment_equations) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleButtons()
    }

    private fun handleButtons() {
        button_0.setOnClickListener { onButtonClick(it) }
        button_1.setOnClickListener { onButtonClick(it) }
        button_2.setOnClickListener { onButtonClick(it) }
        button_3.setOnClickListener { onButtonClick(it) }
        button_4.setOnClickListener { onButtonClick(it) }
        button_5.setOnClickListener { onButtonClick(it) }
        button_6.setOnClickListener { onButtonClick(it) }
        button_7.setOnClickListener { onButtonClick(it) }
        button_8.setOnClickListener { onButtonClick(it) }
        button_9.setOnClickListener { onButtonClick(it) }
        button_backspace.setOnClickListener { }
        button_confirm.setOnClickListener { }
    }

    private fun onButtonClick(button: View) {
        val value = button.tag as Int


    }
}