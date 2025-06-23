/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package concurrency.deadlock

import log

/**
 * Deadlock example
 *
 * Deadlock is a situation when 2 or more threads are blocked and cannot unblock or continue execution.
 */

class Friend(private val name: String) {

    @Synchronized
    fun helloTo(friend: Friend) {
        log("helloTo(\"${friend.name}\") IN")
        Thread.sleep(100)
        friend.helloBack(friend)
        log("helloTo(\"${friend.name}\") OUT")
    }

    @Synchronized
    fun helloBack(friend: Friend) {
        log("helloBack \"${friend.name}\" from ${name}")
    }
}

fun main() {
    log("samples.main() IN")

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

    log("samples.main() OUT")
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