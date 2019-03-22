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

        if (isOperator(operator)) {
            expression.add(operator)
        } else {
            throw Exception("Not a valid operator")
        }
    }

    private fun doOperation(value1: Double, value2: Double, symbol: String): Double {
        when (symbol) {
            "+" -> return value1 + value2
            "-" -> return value1 - value2
            "*" -> return value1 * value2
            "/" -> return value1 / value2
        }
        throw IllegalArgumentException("Invalid operator")
    }

    fun evaluate() {

        if (expression.count() % 2 == 0) {
            throw Exception("Not a valid expression")
        }

        val stack: Stack<Double> = Stack()

        convertInfixToPostfix()

        for (symbol in expression) {
            if (isOperator(symbol)) {
                val value2: Double = stack.pop()
                val value1: Double = stack.pop()
                stack.push(doOperation(value1, value2, symbol))
            } else {
                stack.push(symbol.toDouble())
            }
        }

        result = stack.pop()
    }


    // Algorithm from:
    // https://www.includehelp.com/c/infix-to-postfix-conversion-using-stack-with-c-program.aspx

    private fun isOperator(symbol: String): Boolean {
        if (symbol == "+" ||
                symbol == "-" ||
                symbol == "*" ||
                symbol == "/") {
            return true
        }
        return false
    }

    private fun precedence(symbol: String): Int {
        if (symbol == "*" ||
                symbol == "/") {
            return 2
        }

        if (symbol == "+" ||
                symbol == "-") {
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
