/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines.bacis

import kotlinx.coroutines.*
import log

/**
 * Basic coroutine examples
 *
 * Key points here:
 *
 * runBlocking() - a coroutine builder function which blocks its caller thread
 * launch() - a function which creates and launches a new coroutine
 * delay() and doWork() - suspend functions which don't block underling thread
 * coroutineScope() - a coroutine builder function which allows to start new coroutines limited to this scope
 */
fun main() {
    log("main started")

    runBlocking(CoroutineName("Coroutine #1")) { // coroutine #1
        log("runBlocking() started")

        launch(CoroutineName("Coroutine #2")) {// coroutine #2
           doWork()
        }

        doOtherWork()

        log("runBlocking() finished")
    }

    log("main finished")
}

suspend fun doWork() {
    log("doWork started")
    delay(2*1000)
    log("doWork finished")
}

suspend fun doOtherWork() = coroutineScope {
    log("doOtherWork() started")

    launch(CoroutineName("Coroutine #3")) {// coroutine #3
        delay(2*1000)
        log("1 finished")
    }

    launch(CoroutineName("Coroutine #4")) {// coroutine #4
        delay(2*1000)
        log("2 finished")
    }

    // Here we can retrieve a result
    val job: Deferred<Int> = async(CoroutineName("Coroutine #5") + Dispatchers.Default) { // coroutine #5
        getResult()
    }

    val value = job.await()

    log("doOtherWork() finished, result = $value")
}

suspend fun getResult(): Int {

    log("getResult()")

    delay(1000)

    return 10
}