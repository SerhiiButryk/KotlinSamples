/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines

import kotlinx.coroutines.*

/**
 * Basic coroutine example
 *
 * Key points here:
 * runBlocking - coroutine builder function which blocks its caller function
 * launch - function which creates and launches a coroutine
 * delay() and doWork() - suspend functions which don't block underling thread
 * coroutineScope() - coroutine builder function which allows to start new coroutines
 */
fun main() {
    log("main started")

    runBlocking { // coroutine #1
        log("runBlocking() started")
        launch {// coroutine #2
           doWork()
        }

        doOtherWork()

        log("runBlocking() finished")
    }

    log("main finished")
}

suspend fun doWork() {
    log("coroutine 1 started")
    delay(2*1000)
    log("coroutine 1 finished")
}

suspend fun doOtherWork() = coroutineScope {
    log("doOtherWork() is started")

    launch {// coroutine #3
        delay(2*1000)
        log("coroutine 2 finished")
    }

    launch {// coroutine #4
        delay(2*1000)
        log("coroutine 3 finished")
    }

    // Here we can retrieve a result
    val job: Deferred<Int> = async(Dispatchers.Default) { // coroutine #5
        getResult()
    }

    val value = job.await()

    log("doOtherWork() is finished, result = $value")
}

suspend fun getResult(): Int {

    log("getResult()")

    delay(1000)

    return 10
}

fun log(message: String) {
    println("[Thread ${Thread.currentThread().id}] $message")
}