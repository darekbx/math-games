package com.darekbx.mathgames.ui.equations

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.darekbx.mathgames.R
import kotlinx.android.synthetic.main.fragment_equations.*

class EquationsFragment : Fragment(R.layout.fragment_equations) {

    companion object {
        val CORRECT_COUNT_KEY = "correct_count_key"
        val ALL_COUNT_KEY = "all_count_key"
        val LAST_WRONG_KEY = "last_wrong_key"
    }

    private var level = 1
    private var correctAnswers = 0

    private fun TextView.textString() = this.text.toString()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleButtons()

        refreshStatistics()
        generateNewEquation()
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
        button_backspace.setOnClickListener { removeLastDigit() }
        button_minus.setOnClickListener { addMinus() }
        button_confirm.setOnClickListener { checkResult() }
    }

    private fun checkResult() {
        val equation = equation_view.textString()
        val result = result_view.textString().toInt()
        val resultWrapper = validator.validate(equation, result)

        when (resultWrapper.result) {
            true -> {
                increaseCorrectCount()

                correctAnswers++
                if (correctAnswers % 10 == 0) {
                    level++
                }
            }
            else -> {
                saveLastWrongAnswer(equation, result, resultWrapper.equationResult)
                Toast.makeText(
                    context,
                    "${equation} != ${result} (correct: ${resultWrapper.equationResult})",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        increaseAllCount()

        refreshStatistics()
        generateNewEquation()
        result_view.setText("")
    }

    private fun generateNewEquation() {
        val equation = generator.generate(level)
        equation_view.setText(equation)

        displayLevelInfo()
    }

    private fun displayLevelInfo() {
        val levelCompletion = (correctAnswers - (level - 1) * 10) * 10
        level_view.setText("${getString(R.string.level)} $level ($levelCompletion%)")
    }

    private fun refreshStatistics() {
        val correctCount = getCorrectAnswersCount()
        val allCount = getAllAnswersCount()
        statistics_view.setText("$correctCount / $allCount")
    }

    private fun onButtonClick(button: View) {
        appendDigit("${button.tag}")
    }

    private fun removeLastDigit() {
        val number = result_view.text.toString()
        if (number.length > 0) {
            result_view.setText(number.dropLast(1))
        }
    }

    private fun appendDigit(digit: String) {
        result_view.setText(result_view.text.toString() + digit)
    }

    private fun addMinus() {
        val number = result_view.text.toString()
        if (number.length == 0) {
            result_view.setText("-")
        }
    }

    private fun getCorrectAnswersCount() = preferences?.getInt(CORRECT_COUNT_KEY, 0) ?: 0
    private fun getAllAnswersCount() = preferences?.getInt(ALL_COUNT_KEY, 0) ?: 0

    private fun increaseCorrectCount() {
        val actualCount = getCorrectAnswersCount()
        preferences?.edit()?.putInt(CORRECT_COUNT_KEY, actualCount + 1)?.apply()
    }

    private fun increaseAllCount() {
        val actualCount = getAllAnswersCount()
        preferences?.edit()?.putInt(ALL_COUNT_KEY, actualCount + 1)?.apply()
    }

    private fun saveLastWrongAnswer(equation: String, result: Int, correctResult: Int) {
        preferences?.edit()?.putStringSet(LAST_WRONG_KEY, setOf(
            "Equation: $equation",
            "Result: $result",
            "Correct result: $correctResult"
        ))?.apply()
    }

    private val preferences by lazy { context?.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE) }
    private val generator by lazy { EquationGenerator() }
    private val validator by lazy { EquationValidator() }
}