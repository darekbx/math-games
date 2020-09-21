package com.darekbx.mathgames.ui.equations

import kotlin.math.max
import kotlin.random.Random

class EquationGenerator {

    private val initialValue = 10
    private val modificators = listOf('+', '-', '*')

    fun generate(level: Int): String {
        val maximum = initialValue + level

        val minimumFirst = level - 1
        val minimumSecond = level + 1
        val minimumThird = level

        val firstNumber = randomInt(minimumFirst, maximum)
        val secondNumber = randomInt(minimumSecond, maximum)
        val thirdNumber = randomInt(minimumThird, maximum)
        val firstModificator = randomModificator()
        val secondModificator = randomModificator()

        return "$firstNumber $firstModificator $secondNumber $secondModificator $thirdNumber"
    }

    private fun randomModificator() = modificators[Random.nextInt(modificators.size)]

    private fun randomInt(minimum: Int, maximum: Int) = Random.nextInt(max(minimum, 1), minimum + maximum)
}
