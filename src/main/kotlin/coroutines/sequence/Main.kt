/*
 * Copyright 2025. Happy coding ! :)
 * Author: Serhii Butryk
 */

package coroutines.sequence

/**
 * A Kotlin sequence is a similar concept to a collection (like List or
 * Set), but it is evaluated lazily, meaning the next element is always
 * calculated on demand, when it is needed.
 */
fun main() {

    val list = sequence {
        println("Generating one")
        yield(1)
        println("Generating two")
        yield(2)
        println("Generating three")
        yield(3)
    }

    // Values are generated on demand
    for (i in list) {
        println(i)
    }

}