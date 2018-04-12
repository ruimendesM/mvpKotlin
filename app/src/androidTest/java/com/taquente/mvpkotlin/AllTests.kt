package com.taquente.mvpkotlin

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * This will run All tests for the application.
 *
 * Please note that in order for the UI tests to pass, you will need to disable animations on your testing device.
 * For that, go to Developer Options and turn off the following options:
 * 1- Window animation scale
 * 2- Transition animation scale
 * 3- Animator duration scale
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(/*put test classes here*/)
class AllTests