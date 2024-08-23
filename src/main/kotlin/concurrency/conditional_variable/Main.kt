/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package concurrency.conditional_variable

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock
import kotlin.random.Random

/**
 * Conditional variable example
 *
 * An idea is to block a thread until some condition or conditions are met
 * Demonstrates how to use conditional variable for suspending and notifying threads about some events
 */

class Queue(size: Int) {

    private val lock = ReentrantLock()

    private val signalBufferIsEmpty = lock.newCondition()
    private val signalBufferIsFull = lock.newCondition()

    private val buffer = Array(size) { 0 }
    private var lastElementPointer: Int = 0

    fun add(data: Int) {
        lock.withLock {
            println("add() ${buffer.size} $lastElementPointer IN")

            while (lastElementPointer == buffer.size) {
                // Need to wait
                println("add() waiting...")
                signalBufferIsFull.await()
            }

            println("add() proceed")

            buffer[lastElementPointer++] = data

            signalBufferIsEmpty.signal()

            println("add() ${buffer.size} $lastElementPointer OUT")
        }
    }

    fun getFirst(): Int {
        lock.withLock {

            println("getFirst() ${buffer.size} $lastElementPointer IN")

            while (lastElementPointer == 0) {
                // Need to wait
                println("getFirst() waiting...")
                signalBufferIsEmpty.await()
            }

            println("getFirst() proceed")

            val first = buffer[0]

            lastElementPointer -= 1

            signalBufferIsFull.signal()

            println("getFirst() ${buffer.size} $lastElementPointer OUT")

            return first
        }

    }

}

fun main() {

    val queue = Queue(10)

    for (i in 0..5) {

        thread {
            queue.add(Random(19999999).nextInt(20))
        }

        thread {
            Thread.sleep(10) // Get some time for the first thread to get started
            queue.getFirst()
        }
    }

}