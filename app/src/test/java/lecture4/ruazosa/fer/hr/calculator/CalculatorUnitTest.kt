package lecture4.ruazosa.fer.hr.calculator

import org.junit.Assert
import org.junit.Test

/**
 * Created by dejannovak on 25/03/2018.
 */
class CalculatorUnitTest {
    @Test
    fun test_reset() {
        Calculator.reset()
        Assert.assertEquals(0.0, Calculator.result, 0.0)
        Assert.assertEquals(0, Calculator.expression.count())
    }

    @Test
    fun test_addNumber() {
        Calculator.reset()
        try {
            Calculator.addNumber("Not a number")
            Assert.fail()
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not valid number")
        }
        Calculator.reset()

        try {
            Calculator.addNumber("100.00")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not a valid order of numbers in expression")
        }
        Calculator.reset()

        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.fail()
        }

    }

    @Test
    fun test_addOperator() {
        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.fail()
        }
        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("*")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not a valid operator")
        }

        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.fail()
        }
        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addOperator("-")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not a valid order of operator in expression")
        }
    }

    @Test
    fun test_evaluate() {
        Calculator.reset()
        Calculator.addNumber("100")
        Calculator.addOperator("+")
        Calculator.addNumber("200")
        Calculator.addOperator("-")
        Calculator.addNumber("300")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 0.00, 0.00)
    }

    @Test
    fun test_evaluate_multiply() {
        Calculator.reset()
        Calculator.addNumber("100")
        Calculator.addOperator("*")
        Calculator.addNumber("3")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 300.0, 0.00)

        Calculator.reset()
        Calculator.addNumber("20")
        Calculator.addOperator("*")
        Calculator.addNumber("3.5")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 70.0, 0.00)
    }

    @Test
    fun test_evaluate_divide() {
        Calculator.reset()
        Calculator.addNumber("20")
        Calculator.addOperator("/")
        Calculator.addNumber("2")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 10.0, 0.00)

        Calculator.reset()
        Calculator.addNumber("75")
        Calculator.addOperator("/")
        Calculator.addNumber("5")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 15.0, 0.00)
    }

    @Test
    fun test_evaluate_mix() {
        Calculator.reset()
        Calculator.addNumber("2")
        Calculator.addOperator("+")
        Calculator.addNumber("3")
        Calculator.addOperator("*")
        Calculator.addNumber("5")
        Calculator.addOperator("-")
        Calculator.addNumber("6")
        Calculator.addOperator("/")
        Calculator.addNumber("2")
        Calculator.addOperator("-")
        Calculator.addNumber("1")
        Calculator.addOperator("+")
        Calculator.addNumber("3")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 16.0, 0.00)
    }

}