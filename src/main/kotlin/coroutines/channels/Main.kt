/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines.channels

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import log

/**
 * Channels
 *
 * There are 4 types of channels:
 * 1. Buffered
 * 2. Unlimited
 * 3. Rendezvous
 * 4. Conflated
 */

fun main() {

    // has a buffer of 10 elements
    val buffered = Channel<Int>(10)
    // a rendezvous channel, one of send() or receive() calls is always suspended
    val rendezvous = Channel<Int>()
    // has indefinite buffer size
    val unlimited = Channel<Int>(Channel.UNLIMITED)
    // has a buffer size of 1 and every send() call overrides the previous element
    val conflated = Channel<Int>(Channel.CONFLATED)

    runBlocking<Unit> {

        val channel = Channel<String>(2)

        launch(CoroutineName("Produce1")) {
            log("Sending A1...")
            channel.send("A1")
            log("Sending A2...")
            channel.send("A2")
            log("A done")
        }

        launch(CoroutineName("Produce2")) {
            log("Sending B1...")
            channel.send("B1")
            log("B done")
        }

        launch(CoroutineName("Produce3")) {
            log("Sending B1...")
            channel.send("B1")
            log("B done")
        }

        launch(CoroutineName("Consumer1")) {
            repeat(4) {
                log("Receiving...")
                val x = channel.receive()
                log("Received: $x")
            }
        }
    }

}