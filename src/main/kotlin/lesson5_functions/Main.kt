/**
 * Copyright 2021. Happy codding ! :)
 * Author: Serhii Butryk
 */
package lesson5_functions

import java.lang.StringBuilder

/**
 * Kotlin has many brilliant features when working with functions.
 *
 * 1) Named arguments
 *   You can assign name to function arguments during its invocation.
 *   This helps to make code more readable and avoid mistakes.
 *   For example, typical mistakes are when parameters order has changed and arguments order hasn't.
 *   This feature is very helpful when function has default arguments.
 *
 * 2) Default arguments
 *   You can specify default argument for function parameters.
 *   However, you should explicitly provide all arguments to function when calling it from Java code.
 *   To avoid this you can use @JvmOverloads annotation.
 *
 *  3) Extension functions
 *   This is functions which can be invoked as class member functions, but which are declared outside of class.
 *   Imagine when you have a lot of Java code which can't be rewritten to Kotlin.
 *   Though you want to use Kotlin power in Java code. Extension functions is a way to do this.
 *
 *   Rules:
 *      1) Extension functions has access only to public member of a class (can't call protected private methods)
 *      2) Extension functions can be used for Java and Kotlin classes
 */

/**
 * The function changes default output of toString() method for collections
 */
fun <T> joinToString(collection: Collection<T>,
                     separator: String = ", ",
                     prefix: String = "",
                     postfix: String = ""): String {

    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

/**
 * Simple extension function for String class
 * String is receiver object
 */
fun String.myLastIndex(): Char = get(length - 1)

fun main() {
    val list = listOf(1,2,3)

    println(joinToString(list))

    // Or with named arguments
    println(joinToString(list, separator = ";", prefix = "[", postfix = "]"))

    // extension function
    val lastChar = "hello".myLastIndex()
    println(lastChar)
}