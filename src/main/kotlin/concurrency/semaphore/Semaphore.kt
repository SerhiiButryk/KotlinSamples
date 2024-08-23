/*
 * Copyright 2024. Happy coding ! :)
 * Author: Serhii Butryk
 */

package concurrency.semaphore

import log
import java.util.concurrent.Semaphore
import kotlin.concurrent.thread

/**
 * Semaphore example
 *
 * An idea of Semaphore is to limit access from different threads to the same resource or object.
 * Also, it can be used pretty much like mutex if the permit is 1.
 */
class Resource {

    private val threadAccessGuard = Semaphore(1)

    fun getResource() {
        log("accessResource()")
        threadAccessGuard.acquire()
        log("accessResource() got a resource")
    }

    fun returnResource() {
        log("returnResource()")
        threadAccessGuard.release()
        log("returnResource() returned a resource")
    }
}

fun main() {
    // Simulate creation of some resource
    val resource = Resource()
    // Launch threads and try to access a resource concurrently
    for (i in 1..10) {
        thread {
            resource.getResource()
            Thread.sleep(100)
            resource.returnResource()
        }
    }
}