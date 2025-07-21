/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package lesson20_generics

import java.lang.IllegalArgumentException

/**
 * Generics
 *
 * 1. Type names cam be used in classes, methods, interfaces and extensions function & properties
 * 2. You can't create a generic property which isn't an extension
 * 3. Reified keyword can be used with inline function.
 *
 * All generic classes by default are invariant. You can make them covariant using out keyword.
 *
 */

fun main() {

    // 1. Generic type is inferred by compiler
    val listA = listOf(1, 2, 4)
    printSum(listA)

    // 2. Type checking
    println(listA.isType<String>())
    println(listA.isType<Int>())

    printClass<String>()

}

fun printSum(c: Collection<*>) {
    // This is impossible to know at runtime what the actual type of elements in the
    // list because of type eraser , that's why this type of check are unsafe
    val list = c as? List<Int> ?: throw IllegalArgumentException("List is expected")
    println("Sum: ${list.sum()}")
}

/**
 * You can check value type!
 */
inline fun <reified T> List<*>.isType(): Boolean {
    for (element in this) {
        return element is T
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