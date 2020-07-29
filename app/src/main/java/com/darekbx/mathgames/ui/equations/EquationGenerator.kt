package com.darekbx.mathgames.ui.equations

import kotlin.random.Random

class EquationGenerator {

    private val initialValue = 10
    private val modificators = listOf('+', '-', '*')

    fun generate(level: Int): String {
        val maximum = initialValue + level
        val firstNumber = randomInt(maximum)
        val secondNumber = randomInt(maximum)
        val thirdNumber = randomInt(maximum)
        val firstModificator = randomModificator()
        val secondModificator = randomModificator()
        return "$firstNumber $firstModificator $secondNumber $secondModificator $thirdNumber"
    }

    private fun randomModificator() = modificators[Random.nextInt(modificators.size)]

    private fun randomInt(maximum: Int) = Random.nextInt(1, maximum)
}