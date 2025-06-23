/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines.bacis

import kotlinx.coroutines.*
import log
import logDebug
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume

/**
 * Lesson 1 - Basics
 *
 * Key concepts:
 * 1. Coroutine is an instance of a code which can be run or suspended or completed
 * 2. Coroutine scope is an entity inside which coroutines of the same hierarchy lives and exists
 * 3. runBlocking(), launch() and async() - coroutine builder functions which creates a coroutine
 * 4. It has its onw Coroutine context, and we can override some configs in it
 * 5. It knows about its parent and child coroutines
 * 6. It may run concurrently but not always in parallel with other code
 * 7. It can stop at the suspension points and then resume its execution
 */
fun main() {
//    Uncomment to play with it
//    example1_canceletion()
//    example2_yield()
//    example3_async()
}

/**
 * Cancellation example
 */
fun example1_canceletion() = runBlocking(CoroutineName("Coroutine#1")) {

    logDebug("example1_canceletion() Started")

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

    job2.invokeOnCompletion {
        if (it == null)
            logDebug("Job2 completed successfully")
        else
            logDebug("Job2 failed")
    }

    delay(1000)

    // Cancels coroutine 3
    job2.cancel()

    logDebug("End")

}

/**
 * yield() example
 */
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

/**
 * Async example
 */
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

private val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "Schedule-Thread").apply { isDaemon = true }
}

/**
 * suspendCancellableCoroutine()
 * suspendCoroutine()
 */
/*
suspend fun main() {

    log("Start")

    suspendCancellableCoroutine<Unit> { continuation ->
        executor.schedule({
            log("Resume after 1 second")

            continuation.resume(Unit)

            continuation.invokeOnCancellation { log("Invoked on cancellation is called") }

        }, 1000, TimeUnit.MILLISECONDS)
    }

    log("End")

}*/
