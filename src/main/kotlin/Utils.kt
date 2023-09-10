/*
 *  Copyright 2022. Happy coding ! :)
 *  Author: Serhii Butryk
 */

fun <T: Any> log(message: T) {
    println("[THREAD-${Thread.currentThread().id}] *** $message")
}
