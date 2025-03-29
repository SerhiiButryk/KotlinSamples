/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines.context_and_scope

import kotlinx.coroutines.*
import log

/**
 * Coroutine scope and context
 *
 * Links:
 * https://elizarov.medium.com/coroutine-context-and-scope-c8b255d59055
 */
fun main() = runBlocking {

    log("My context - $coroutineContext")
    println("My job is: ${coroutineContext[Job]}")

    work()
    work()
}

suspend fun work() = coroutineScope {
    launch {
        delay(1000L)
        log("Task completed! $coroutineContext")
    }
}