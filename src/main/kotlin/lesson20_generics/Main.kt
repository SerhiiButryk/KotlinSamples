/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package lesson20_generics

import java.lang.IllegalArgumentException

/**
 * Templates
 *
 * 1. Type names cam be used in class, method, interface and extension function
 * 2. You cannot create a template property
 *
 * 3. Reified keyword. You cannot check type of template value during runtime because
 * this information is not available. But if you have inline function you can do that.
 */

fun printSum(c: Collection<*>) {
    val list = c as? List<Int> ?: throw IllegalArgumentException("List is expected")
    println("Sum: ${list.sum()}")
}

/**
 * You can check value type!
 */
inline fun <reified T> List<*>.isType(): Boolean {
    for (element in this) {
        if (element is T) {
            return true
        }
    }
    return false
}

/**
 * This code doesn't compile
 */
//fun <T> List<*>.isType2(): Boolean {
//    for (element in this) {
//        if (element is T) {
//            return true
//        }
//    }
//    return false
//}

/**
 * Another example
 */
 inline fun <reified T> printClass() {
    println(T::class.java.canonicalName)
}

fun main() {

    val listA = listOf(1, 2, 4)
    printSum(listA)

    // ClassCastException will be thrown
//    val listB = listOf("")
//    printSum(listB)

    // IllegalArgumentException will be thrown
//    val set = setOf("Hello", "Hello")
//    printSum(set)

    println(listA.isType<String>())

    printClass<String>()
}