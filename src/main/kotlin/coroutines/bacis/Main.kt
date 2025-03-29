/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines.bacis

import kotlinx.coroutines.*
import log

/**
 * Lesson 1 - Basics
 *
 * Key concepts:
 * 1. Coroutine is an instance of a code which can be run or suspended or completed
 * 2. Coroutine scope is an entity inside which coroutines of the same hierarchy lives and exists
 * 3. runBlocking(), launch() and async() - coroutine builder functions which creates, sets up and starts coroutines
 * 4. Coroutine has its onw context, and we can override some configs in it
 * 5. Coroutine knows about its parent and child coroutines
 * 6. Coroutine may run concurrently but not always in parallel with other code
 * 7. Coroutine can stop at the suspension points and then resume its execution
 */
fun main(): Unit {

//    Uncomment to play with it
//    example1_canceletion()
//    example2_yield()
//    example3_async()

}

fun example1_canceletion() = runBlocking(CoroutineName("Coroutine#1")) {

    logDebug("Running")

    val job1 = launch(CoroutineName("Coroutine#2")) {
        logDebug("Running")
        delay(1000)
        logDebug("End")
    }

    val job2 = launch(CoroutineName("Coroutine#3")) {
        logDebug("Running")
        try {
            delay(5000)
        } catch (e: CancellationException) {
            logDebug("Canceled")
            throw e
        }
        logDebug("End")
    }

    delay(1000)

    // Cancels coroutine 3
    job2.cancel()

    logDebug("End")

}

fun example2_yield() = runBlocking(CoroutineName("Coroutine#1")) {

    logDebug("Running")

    val job1 = launch(CoroutineName("Coroutine#2")) {
        logDebug("Running")
        yield() // Ask to suspend and to execute some other tasks
        logDebug("Continue")
        yield() // Ask to suspend and to execute some other tasks
        logDebug("Done!")
    }

    val job2 = launch(CoroutineName("Coroutine#3")) {
        logDebug("Running")
        yield() // Ask to suspend and to execute some other tasks
        logDebug("Continue")
        yield() // Ask to suspend and to execute some other tasks
        logDebug("Done!")
    }

    logDebug("End")

}

fun example3_async() = runBlocking(CoroutineName("Coroutine#1")) {

    logDebug("Running")

    val job1 = launch(CoroutineName("Coroutine#2")) {
        logDebug("Running")
        delay(1000)
        logDebug("End")
    }

    val job2: Deferred<Int> = async(CoroutineName("Coroutine#3")) {
        compute()
    }

    // Cancels coroutine 3
    val result = job2.await()
    logDebug("Result is $result")

    logDebug("End")

}

suspend fun compute(): Int {
    // Long computation
    delay(1000)
    return 10
}

 fun <T> CoroutineScope.logDebug(message: T) {
    log("DEBUG: coroutine: ${coroutineContext[CoroutineName]?.name} = $this, my parent: ${coroutineContext[Job]?.parent}," +
            " --> " + message)
}