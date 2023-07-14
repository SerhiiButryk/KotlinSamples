/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package design_patterns.decorator.notifier_lib

class Notifier : Notify {
    override fun send(message: Message) {
        println("Sending message base...")
    }

    override fun toString(): String {
        return "Notify"
    }
}