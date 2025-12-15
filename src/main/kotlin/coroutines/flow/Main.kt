/*
 * Copyright 2025. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import log

/**
 * Flow
 *
 * 1. What is flow ? Why do we need it ?
 *
 * Flow is a primitive which we can observe to receive multiple asynchronous value.
 * We can use it to do long computations and deliver the result while not blocking the main thread.
 *
 * 2. How does it work ?
 *
 * Flow makes good use of coroutines and channels.
 *
 * 3. What are flow operators ? What are terminal operators ?
 *
 * Flow operators are functions which can be applied on the upstream flow.
 * Every operator does some operations like filtering, modification or manipulation with data.
 * Terminal operation are functions which eventually collects the flow.
 * So, the values can be received by the client code.
 *
 * 4. What is cold/hot flow ?
 *
 * Cold flow doesn't run if there is no collectors, while hot flow does some work as soon as new
 * element/value is assigned.
 * Cold flow is a flow created by flow{...} builder. Hot flows are StateFlow or SharedFlow.
 *
 * Cold data sources are lazy. They can be infinite. Typically, they don't store elements and generate them on demand.
 *
 * 5. Testing flows
 *
 * Use take() or 'background scope' for testing infinite flows
 * Use onStart() onCompletion() to do something in the begging or in the end
 *
 */

// By definition this flow is not like any other coroutine builder.
// It doesn't do processing. It only defines a set of operations which should be
// done when the processing gets triggered by terminal operators.
fun computeSimple(): Flow<Int> = flow {
    log("Flow started")
    for (i in 0..10) {
        delay(100)
        emit(i)
    }
    log("Flow finished")
}

suspend fun getUserName(): String { delay(1000)
    return "UserName" }

/**
 * Simple flow example
 */
suspend fun main() = coroutineScope {

    // Flow is frequently used to represent a single value delayed
    // in time (like a Single in RxJava). So, it makes sense to
    // convert a suspending function into a flow. The result of
    // this function will be the only value in this flow.
    val f = suspend {
        delay(1000)
        "Hello"
    }

    f.asFlow().collect { value -> println(value) }

    // OR REGULAR FUNCTION
    ::getUserName .asFlow()
        .collect { println(it) }

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
fun flowCancellation() = runBlocking {

    log("flowCancellation is started")

    withTimeoutOrNull(100) {
        try {
            computeSimple().collect { v ->
                log("Got $v")
            }
        } catch (e: Exception) {
            log("Got ${e}")
        }
    }

    // Or
//    computeSimple()
//        .takeWhile { it < 5 }
//        .collect { v ->
//        log("Got $v")
//    }

    log("flowCancellation is finished")

}

/**
 * Flow operators
 */
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

fun flowOperators() = runBlocking {
    numbers()
        .take(2) // take only the first two
        .collect { value -> println(value) }
}

fun flowOperatorsAdvance() = runBlocking {

    /**
     * Merge
     * Coroutine Provider gets launched on every flow in the list, sends each value to a channel
     * Coroutine Consumer receives from the channel end emits every value to new Flow
     */

    val flow1 = flowOf(1, 2, 3).onEach { delay(100) }
    val flow2 = flowOf(1.0, 2.0, 3.0)

    val newFlow = merge(flow1, flow2)
    newFlow.collect { println(it) }

    /**
     * REAL EXAMPLE OF FLOW MERGE
     */

//    fun listenForMessages() {
//        merge(userSentMessages, messagesNotifications)
//            .onEach { displayMessage(it) }
//            .launchIn(scope)
//    }

/**
 * Zip
 *
 * Completes when one of the flow completes and close other uncompleted flow.
 */

    val flow3 = flowOf(1, 2, 3, 4)
        .onEach { delay(100) }

    val flow4 = flowOf("A"  , "B", "C")
        .onEach { delay(1000) }

    // Closed when the first flow is finished
    val newFlow2 = flow3.zip(flow4) { a, b -> "$a $b" }
    newFlow2.collect { println(it) }

/**
 * Combine
 *
 * The most recent values update older values in every pair
 * Closed when the two flows are finished
 */

    val flow5 = flowOf(1, 2, 3, 4)
        .onEach { delay(100) }

    val flow6 = flowOf("A"  , "B", "C")
        .onEach { delay(1000) }

    val newFlow3 = flow5.combine(flow6) { a, b -> "$a $b" }
    newFlow3.collect { println(it) }

    // A typical use case might be when a view needs to get either of two observable element changes.
    // For example, when a notification badge depends on both the current state of a user and some notifications,
    // we might observe them both and combine their changes to update a view.

//    userStateFlow
//    .combine(notificationsFlow) { userState, notifications ->
//        updateNotificationBadge(userState, notifications)
//    }
//    .collect()

/**
 * Conflate
 *
 * A way to get only latest values but not all
 */

    val flow = flow {
        for (i in 0..30) {
            delay(100)
            emit(i)
        }
    }

    val list = flow.conflate().onEach { delay(1000) }.toList()
    println(list)

}

/**
 * channelFlow & callbackFlow
 */
fun callbackFlowTest() = runBlocking {

// Use to concurrently produce and consume data
//    channelFlow {  }

//    fun flowFrom(api: CallbackBasedApi): Flow<T> = callbackFlow { val callback = object : Callback {
//        override fun onNextValue(value: T) { trySendBlocking(value)
//        }
//        override fun onApiError(cause: Throwable) {
//            cancel(CancellationException("API Error", cause))
//        }
//        override fun onCompleted() = channel.close() }
//        api.register(callback)
//        awaitClose { api.unregister(callback) }
//    }

}

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