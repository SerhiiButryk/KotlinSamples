/*
 * Copyright 2025. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import log
import kotlin.system.measureTimeMillis

/**
 * Flow
 *
 * 1. What is flow ? Why do we need it ?
 *
 * Flow is a primitive which we can observe to receive multiple asynchronous value.
 * We can use it to make long computations and send the result while not blocking the main thread.
 *
 * 2. How does it work ?
 *
 * Flow makes good use of coroutines and channels.
 *
 * 3. What are flow operators ? What are terminal operators ?
 *
 * Flow operators are functions which can be applied on the upstream flow.
 * Every operator does some operations like filtering, modification or manipulation with data.
 * Terminal operation are functions which eventually collects the flow. So, the values can be received by the client code.
 *
 * 4. What is cold/hot flow ?
 *
 * Cold flow doesn't run if there is no collectors, while hot flow does some work every time when new value is assigned.
 * Cold flow is a flow created by flow{...} builder.
 * Hot flows are StateFlow and SharedFlow.
 *
 */

fun computeSimple(): Flow<Int> = flow {
    log("Flow started")
    for (i in 0..10) {
        delay(100)
        emit(i)
    }
    log("Flow finished")
}

/**
 * Simple flow example
 */

/*
fun samples.main() = runBlocking {

    launch {
        for (i in 0..3) {
            delay(100)
            log("Thread is not blocked...")
        }
    }

    var index = 0

    computeSimple()
        // Remove some values
        .filter { it % 2 == 0 }
        // Modify values or flow
        .map { it * 2 }
        // Change what we receive
        .transform { value ->
            emit(10)
        }
        .collect { v ->
            log("[${index++}] Value = $v")
        }

}
 */

/**
 * Flow cancellation
 */

/*
fun samples.main() = runBlocking {

// Flow builders
//    val flow = (1..3).asFlow()
//    val flow = flowOf(1, 2, 3)

    log("Main is started")

    withTimeoutOrNull(100) {
        try {
            computeSimple().collect { v ->
                log("Got $v")
            }
        } catch (e: Exception) {
            log("Got ${e}")
        }
    }

    log("Main is finished")

}
 */

/**
 * Flow operators
 */

/*
fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not execute")
        emit(3)
    } catch (e: Exception) {
        println("$e")
    } finally {
        println("Finally in numbers")
    }
}

fun samples.main() = runBlocking<Unit> {
    numbers()
        .take(2) // take only the first two
        .collect { value -> println(value) }
}
 */

/*
fun main() = runBlocking {

    // zip()
    println("---- zip() ---")

    var flow1 = (1..5).asFlow()
    var flow2 = flowOf("one", "two", "tree")

    var flow3 = flow1.zip(flow2) { val1, val2 ->
        "$val1 - $val2"
    }

    flow3.collect { println(it) }

    // Here we have some delay but result is the same

    println("---- zip() ---")

    flow1 = (1..3).asFlow().onEach { delay(300) }
    flow2 = flowOf("one", "two", "tree").onEach { delay(400) }

    val time = measureTimeMillis {

        flow3 = flow1.zip(flow2) { val1, val2 ->
            "$val1 - $val2"
        }

        flow3.collect { println(it) }
    }

    println("Collected in $time")

    // combine()

    println("---- combine() ---")

    flow1 = (1..4).asFlow().onEach { delay(300) }
    flow2 = flowOf("one", "two", "tree").onEach { delay(400) }

    val time2 = measureTimeMillis {

        flow3 = flow1.combine(flow2) { val1, val2 ->
            "$val1 - $val2"
        }

        flow3.collect { println(it) }
    }

    println("Collected in $time2")

    // A way to get only latest values but not all

    println("---- conflate() ---")

    val flow = flow {
        for (i in 0..30) {
            delay(100)
            emit(i)
        }
    }

    val list = flow.conflate().onEach { delay(1000) }.toList()
    println(list)

}*/
