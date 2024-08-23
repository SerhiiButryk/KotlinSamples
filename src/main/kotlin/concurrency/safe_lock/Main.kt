/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package concurrency.safe_lock

import log
import java.util.concurrent.locks.ReentrantLock

/**
 * Safe lock example
 *
 * Demonstrates how to properly use locks and to avoid deadlock
 *
 * The idea is to acquire a lock for every object. If we cannot do
 * that then release locks and stop to give a chance other thread to complete its work
 */
class Friend(private val name: String) {

    private val myLock = ReentrantLock()

    fun helloTo(friend: Friend) {
        log("helloTo(\"${friend.name}\") IN")
        Thread.sleep(10) // Some long operations
        if (canHello(friend)) {
            try {
                log("helloTo(\"${friend.name}\") ready for hello")
                friend.helloBack(friend)
            } finally {
                myLock.unlock()
                friend.myLock.unlock()
            }
        } else {
            log("helloTo(\"${friend.name}\") cannot acquire lock, return")
        }
        log("helloTo(\"${friend.name}\") OUT")
    }

    fun helloBack(friend: Friend) {
        log("helloBack \"${friend.name}\" from ${name}")
    }

    private fun canHello(friend: Friend): Boolean {

        var canGetMyLock = false
        var canGetYourLock = false

        try {

            canGetMyLock = myLock.tryLock()
            canGetYourLock = friend.myLock.tryLock()

        } finally {
            if (!(canGetMyLock && canGetYourLock)) {

                if (canGetMyLock) {
                    myLock.unlock()
                }

                if (canGetYourLock) {
                    friend.myLock.unlock()
                }
            }

        }

        return canGetMyLock && canGetYourLock
    }
}

fun main() {
    log("main() IN")

    val alice = Friend("Alice")
    val john = Friend("John")

    val thread1 = Thread {
        log("Thread 1 is started")
        alice.helloTo(john)
        log("Thread 1 is finished")
    }

    thread1.start()

    val thread2 = Thread() {
        log("Thread 2 is started")
        john.helloTo(alice)
        log("Thread 2 is finished")
    }

    thread2.start()

    log("main() OUT")
}

fun logTreadState(thread: Thread) {
    log(
        "tid = ${thread.id} " +
                "isAlive = ${thread.isAlive} " +
                "state = ${thread.state} " +
                "isDaemon = ${thread.isDaemon} " +
                "priority = ${thread.priority}"
    )
}