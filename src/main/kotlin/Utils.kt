/*
 *  Copyright 2022. Happy coding ! :)
 *  Author: Serhii Butryk
 */
import java.text.SimpleDateFormat
import java.util.*

fun <T: Any> log(message: T) {
    println("[${SimpleDateFormat("hh:mm:ss").format(Date())} " +
            "THREAD-${Thread.currentThread().id}]-${Thread.currentThread().name} $message")
}
