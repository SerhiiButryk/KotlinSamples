/*
 * Copyright 2025. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines.flow

import kotlinx.coroutines.coroutineScope
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
 * We can use it to make long computations and deliver the result while not blocking the main thread.
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
 * Cold flow doesn't run if there is no collectors, while hot flow does some work as soon as new element/value is assigned.
 * Cold flow is a flow created by flow{...} builder. Examples of hot flows are StateFlow and SharedFlow.
 *
 * Cold data sources are lazy. They can be infinite. Typically, they don't store elements and generate them on demand.
 *
 */

// By definition this flow is not like any other coroutine builder.
// It doesn't do processing. It only defines a set of operations which should be
// done when the processing gets triggered by calling terminal operators.
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
suspend fun main() = coroutineScope {

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

/**
 * Flow cancellation
 */

/*
fun main() = runBlocking {

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

/**
 * Merge
 */

//    val flow1 = flowOf(1, 2, 3).onEach { delay(100) }
//    val flow2 = flowOf(1.0, 2.0, 3.0)
//
//    val newFlow = merge(flow1, flow2)
//    newFlow.collect { println(it) }

//     REAL EXAMPLE OF FLOW MERGE

//    fun listenForMessages() {
//        merge(userSentMessages, messagesNotifications)
//            .onEach { displayMessage(it) }
//            .launchIn(scope)
//    }

/**
 * Zip
 */

//    val flow1 = flowOf(1, 2, 3, 4)
//        .onEach { delay(100) }
//
//    val flow2 = flowOf("A"  , "B", "C")
//        .onEach { delay(1000) }
//
//    // Closed when the first flow is finished
//    val newFlow = flow1.zip(flow2) { a, b -> "$a $b" }
//        newFlow.collect { println(it) }

/**
 * Combine
 */

//    val flow1 = flowOf(1, 2, 3, 4)
//        .onEach { delay(100) }
//
//    val flow2 = flowOf("A"  , "B", "C")
//        .onEach { delay(1000) }
//
//    // The most recent values update older values in every pair
//    // Closed when the two flows are finished
//    val newFlow = flow1.combine(flow2) { a, b -> "$a $b" }
//    newFlow.collect { println(it) }

//    // A typical use case might be when a view needs to be either of two observable element changes.
//    // For example, when a notification badge depends on both the current state of a user and some notifications,
//    // we might observe them both and combine their changes to update a view.
//    userStateFlow
//    .combine(notificationsFlow) { userState, notifications ->
//        updateNotificationBadge(userState, notifications)
//    }
//    .collect()

    // Possible to convert suspend functions to Flow
    val f = suspend {
        delay(1000)
        "Hello"
    }

    f.asFlow().collect { value -> println(value) }

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

/**
 * channelFlow & callbackFlow
 */
/*
fun main() = runBlocking {

// Use to concurrently produce and consume data
//    channelFlow {  }

    fun flowFrom(api: CallbackBasedApi): Flow<T> = callbackFlow { val callback = object : Callback {
        override fun onNextValue(value: T) { trySendBlocking(value)
        }
        override fun onApiError(cause: Throwable) {
            cancel(CancellationException("API Error", cause))
        }
        override fun onCompleted() = channel.close() }
        api.register(callback)
        awaitClose { api.unregister(callback) }
    }

}
*/

/**
 * flat map operations:
 */

/*
fun main() = runBlocking {

    // Processes elements sequentially

    val transform = fun (element: String)
        = flowOf(1, 2, 3)
            .onEach { delay(1000) }
            .map { "$it $element" }

    flowOf("A", "B", "C")
        .flatMapConcat { transform(it) }
        .collect { println(it) }

    // Processes elements concurrently

    // The typical use of flatMapMerge is when we need to request data for each element in a flow.
    // For instance, we have a list of categories, and you need to request offers for each of them.
    // You already know that you can do this with the async function.
    // There are two advantages of using a flow with flatMapMerge instead:

    // • we can control the concurrency parameter and decide how many categories
    // we want to fetch at the same time (to avoid sending hundreds of requests at the same time);

    // • we can return Flow and send the next elements as they arrive
    // (so, on the function-use side, they can be handled immediately).

    //    suspend fun getOffers( categories: List<Category>
    //    ): Flow<Offer> = categories
    //    .asFlow()
    //    .flatMapMerge(concurrency = 20) {
    //        suspend { api.requestOffers(it) }.asFlow()
    //        // or flow { emit(api.requestOffers(it)) }
    //

/*
    val transform = fun (element: String)
        = flowOf(1, 2, 3)
            .onEach { delay(1000) }
            .map { "$it $element" }

    flowOf("A", "B", "C")
        .flatMapMerge { transform(it) }
        .collect { println(it) }
 */

    // The last one is flatMapLatest. It forgets about the previous flow once a new one appears.

 /*
    val transform = fun (element: String)
        = flowOf(1, 2, 3)
            .onEach { delay(1000) }
            .map { "$it $element" }

    flowOf("A", "B", "C")
        .flatMapLatest { transform(it) }
        .collect { println(it) }
 */

}
*/