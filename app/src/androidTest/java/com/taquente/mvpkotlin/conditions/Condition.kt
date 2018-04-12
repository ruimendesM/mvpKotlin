package com.taquente.mvpkotlin.conditions

/**
 * Interface for Conditions
 */
interface Condition {
    fun checkCondition(): Boolean

    fun getDescription(): String
}