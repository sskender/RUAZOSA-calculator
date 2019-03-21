package lecture4.ruazosa.fer.hr.calculator

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
            "+" -> expression.add(operator)
            "-" -> expression.add(operator)
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
}
