package com.taquente.hwcodechallenge.utils

import com.taquente.hwcodechallenge.conditions.AnyCondition
import com.taquente.hwcodechallenge.conditions.Condition
import com.taquente.hwcodechallenge.conditions.EveryCondition

object UITestUtils {

    private const val DEFAULT_TIMEOUT_LIMIT = 30000 //ms
    private const val DEFAULT_MAX_WATCHER_INTERVAL = 250 //ms
    private const val MIN_WATCH_INTERVAL = 20 //ms
    private const val WATCH_INTERVAL_MULTIPLIER = 2

    @JvmOverloads
    fun waitForCondition(condition: Condition, timeout: Int = DEFAULT_TIMEOUT_LIMIT) {
        waitForCondition(condition, DEFAULT_MAX_WATCHER_INTERVAL, timeout)
    }

    private fun waitForCondition(condition: Condition, maxWatchInterval: Int, timeout: Int) {
        var elapsedTime = 0
        var waitingTime = MIN_WATCH_INTERVAL

        do {
            if (condition.checkCondition()) {
                break
            }
            sleep(waitingTime.toLong())
            elapsedTime += waitingTime
            waitingTime = Math.min(maxWatchInterval, waitingTime * WATCH_INTERVAL_MULTIPLIER)

            if (elapsedTime >= timeout) {
                throw RuntimeException("The test stopped because it took more than " + timeout / 1000 + " seconds" + "\r\n"
                        + condition.getDescription())
            }
        } while (true)
    }

    fun anyCondition(vararg conditions: Condition): Condition {
        return AnyCondition(conditions)
    }

    fun everyCondition(vararg conditions: Condition): Condition {
        return EveryCondition(conditions)
    }

    /**
     * Sleep Thread for the given amount of millis.
     */
    fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            throw RuntimeException("Cannot execute Thread.sleep()")
        }

    }
}