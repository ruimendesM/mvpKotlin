package com.taquente.hwcodechallenge.conditions

/**
 * Condition implementation where all of the conditions should be matched.
 */
class EveryCondition(private val conditions: Array<out Condition>) : Condition {

    override fun checkCondition(): Boolean {
        for (condition in conditions) {
            if (!condition.checkCondition()) {
                return false
            }
        }

        return true
    }

    override fun getDescription(): String {
        val description = StringBuilder()

        for (singleCondition in conditions) {
            description.append(singleCondition.getDescription()).append("\r\n")
        }

        return description.toString()
    }
}