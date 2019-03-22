package lecture4.ruazosa.fer.hr.calculator

import java.util.*

/**
 * Created by dejannovak on 24/03/2018.
 */
object Calculator {

    var result: Double = 0.0
        private set

    var expression: MutableList<String> = mutableListOf()
        private set

    fun reset() {
        result = 0.0
        expression = mutableListOf()
    }

    fun addNumber(number: String) {
        try {
            val num = number.toDouble()
        } catch (e: NumberFormatException) {
            throw Exception("Not valid number")
        }

        if (expression.count() % 2 == 0) {
            expression.add(number)
        } else {
            throw Exception("Not a valid order of numbers in expression")
        }
    }

    fun addOperator(operator: String) {
        if (expression.count() % 2 != 1) {
            throw Exception("Not a valid order of operator in expression")
        }
        when (operator) {
            R.string.plus_sign.toString() -> expression.add(operator)
            R.string.minus_sign.toString() -> expression.add(operator)
            R.string.multiply_sign.toString() -> expression.add(operator)
            R.string.divide_sign.toString() -> expression.add(operator)
            else -> {
                throw Exception("Not a valid operator")
            }
        }
    }

    fun evaluate() {

        if (expression.count() % 2 == 0) {
            throw Exception("Not a valid expression")
        }

        result = expression[0].toDouble()

        for (i in 1 until expression.count() - 1 step 2) {
            when (expression[i]) {
                "+" -> result += expression[i + 1].toDouble()
                "-" -> result -= expression[i + 1].toDouble()
            }
        }
    }


    // Algorithm from:
    // https://www.includehelp.com/c/infix-to-postfix-conversion-using-stack-with-c-program.aspx

    private fun isOperator(symbol: String): Boolean {
        if (symbol == R.string.plus_sign.toString() ||
                symbol == R.string.minus_sign.toString() ||
                symbol == R.string.multiply_sign.toString() ||
                symbol == R.string.divide_sign.toString()) {
            return true
        }
        return false
    }

    private fun precedence(symbol: String): Int {
        if (symbol == R.string.multiply_sign.toString() ||
                symbol == R.string.divide_sign.toString()) {
            return 2
        }

        if (symbol == R.string.plus_sign.toString() ||
                symbol == R.string.divide_sign.toString()) {
            return 1
        }

        return 0
    }

    private fun convertInfixToPostfix() {
        // stack for conversion algorithm and
        // new expression list for postfix values
        val stack: Stack<String> = Stack()
        val newExpression: MutableList<String> = mutableListOf()

        for (i in 0 until expression.size) {
            val symbol = expression[i]

            if (isOperator(symbol)) {
                // pop and push == peek
                while (!stack.empty() && isOperator(stack.peek()) && precedence(stack.peek()) >= precedence(symbol)) {
                    newExpression.add(stack.pop())
                }

                stack.add(symbol)
            } else {
                newExpression.add(symbol)
            }
        }

        // add remaining elements to expression list
        while (!stack.empty()) {
            newExpression.add(stack.pop())
        }

        // update expression list
        expression = newExpression
    }

}
