package com.taquente.mvpkotlin.conditions

/**
 * Condition implementation where at least one of the conditions should be matched.
 */
class AnyCondition(private val conditions: Array<out Condition>) : Condition {

    override fun checkCondition(): Boolean {
        for (condition in conditions) {
            if (condition.checkCondition()) {
                return true
            }
        }

        return false
    }

    override fun getDescription(): String {
        val description = StringBuilder()

        for (singleCondition in conditions) {
            description.append(singleCondition.getDescription()).append("\r\n")
        }

        return description.toString()
    }

}