/*
 *  Copyright 2022. Happy coding ! :)
 *  Author: Serhii Butryk
 */

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import java.text.SimpleDateFormat
import java.util.*

fun <T : Any> log(message: T) {
    println(
        "[${SimpleDateFormat("hh:mm:ss").format(Date())} " +
                "TID:${Thread.currentThread().id} TN:${Thread.currentThread().name}] $message"
    )
}

fun <T> CoroutineScope.logDebug(message: T) {
    log(
        "DEBUG: name: '${coroutineContext[CoroutineName]?.name}' '$this', parent: '${coroutineContext[Job]?.parent}'" +
                ": " + message
    )
}