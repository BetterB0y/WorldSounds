package pl.polsl.worldsounds.models

import kotlin.random.Random

enum class RiddleOperator(val operator: String) {
    PLUS("+"),
    MINUS("-"),
    TIMES("*")
}

data class RiddleData(
    val num1: Int,
    val num2: Int,
    val operator: RiddleOperator,
    val answer: Int
) {
    companion object {
        fun default(): RiddleData {
            return RiddleData(0, 0, RiddleOperator.PLUS, 0)
        }

        fun generate(): RiddleData {
            val num1 = generateNumber()
            val num2 = generateNumber()
            val operator = RiddleOperator.values().random()

            val answer = when (operator) {
                RiddleOperator.PLUS -> num1 + num2
                RiddleOperator.MINUS -> num1 - num2
                RiddleOperator.TIMES -> num1 * num2
            }
            return RiddleData(num1, num2, operator, answer)
        }

        private fun generateNumber(): Int {
            return Random.nextInt(1, 11)
        }
    }

    fun isAnswerCorrect(answer: String): Boolean {
        return answer.toIntOrNull() == this.answer
    }
}
