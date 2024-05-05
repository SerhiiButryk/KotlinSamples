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
    val scope = CoroutineScope(Dispatchers.Default)

    log("My context - $coroutineContext")
    println("My job is: ${coroutineContext[Job]}")

    val job = scope.launch {
        delay(1000L)
        log("Task completed! $coroutineContext")
    }

    delay(500L)
    scope.cancel()
    log("Scope canceled")
}