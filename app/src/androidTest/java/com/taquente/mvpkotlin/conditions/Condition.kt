package com.taquente.hwcodechallenge.conditions

/**
 * Interface for Conditions
 */
interface Condition {
    fun checkCondition(): Boolean

    fun getDescription(): String
}