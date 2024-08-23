/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package concurrency.lock

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * Lock example
 *
 * The idea of a lock is to make sure that only one thread can access some code or 'critical section' at any time.
 */
class Person(private val name: String) {

    private val lock = ReentrantLock()

    fun doSomething() {
        println("doSomething() IN")

        lock.lock()

        println("doSomething() got lock, count = " + lock.holdCount)

        try {

            println("doSomething() working...")
            // Simulate some operation
            Thread.sleep(1000)

            process()

        } finally {
            lock.unlock()
            println("doSomething() release lock, count = " + lock.holdCount)
        }
        println("doSomething() OUT")
    }

    private fun process() {
        println("process() IN")
        lock.lock()

        println("process() got lock, count = " + lock.holdCount)

        try {

            println("process() working...")
            // Simulate some operation
            Thread.sleep(1000)

        } finally {
            lock.unlock()

            println("process() released lock, count = " + lock.holdCount)
        }
        println("process() OUT")
    }
}

fun main() {

    val person = Person("John")

    for (i in 0..5) {
        thread {
           println("thread $i started")
           person.doSomething()
            println("thread $i finished")
        }
    }

}