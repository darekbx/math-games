package com.darekbx.mathgames.ui.equations

import org.junit.Test

class EquationValidatorTest {

    @Test
    fun `Validator test`() {
        val validator = EquationValidator()

        assert(validator.validate("4 * 2 * 4", 4 * 2 * 4).result)
        assert(validator.validate("1 + 1 + 1", 1 + 1 + 1).result)
        assert(validator.validate("1 - 1 + 1", 1 - 1 + 1).result)
        assert(validator.validate("1 + 1 - 1", 1 + 1 - 1).result)
        assert(validator.validate("5 - 1 - 1", 5 - 1 - 1).result)
        assert(validator.validate("5 * 2 - 3", 5 * 2 - 3).result)
        assert(validator.validate("5 * 2 + 5", 5 * 2 + 5).result)
        assert(validator.validate("3 + 3 * 5", 3 + 3 * 5).result)
        assert(validator.validate("5 * 2 * 2", 5 * 2 * 2).result)
        assert(validator.validate("1 - 2 * 2", 1 - 2 * 2).result)
        assert(validator.validate("7 - 2 * 2", 7 - 2 * 2).result)
        assert(validator.validate("2 - 2 * 2", 2 - 2 * 2).result)
        assert(validator.validate("2 - 2 - 2", 2 - 2 - 2).result)
        assert(validator.validate("20 * 4 - 40", 20 * 4 - 40).result)
        assert(validator.validate("19 + 37 - 38", 19 + 37 - 38).result)
    }
}
