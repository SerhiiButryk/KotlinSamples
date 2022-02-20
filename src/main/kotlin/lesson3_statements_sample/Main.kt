/**
 * Copyright 2021. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson3_statements_sample

import lesson2_classes_sample.Color
import lesson2_classes_sample.Person
import java.lang.Exception
import java.util.*

/**
 *  Basically, any programming language consists of instructions and statements.
 *  Unlike Java, in Kotlin statements can be instructions.
 *  Statements can't return value, while the instructions can.
 *
 *  Example demonstrates:
 *
 *  1. 'if' statement
 *  2. 'when' statement
 *  3. 'for' statement
 *  4. 'in' operator - to check if a range has a value or not
 */

fun main() {

    val john = Person("John", false)

    // For example, "if" statement can return value.
    val message = if (john.isMarried) "John is married" else "John isn't married"
    println(message)

    // Example of when statement
    // Use with enums
    println("Got word: ${getMnemonic(Color.YELLOW)} for ${Color.YELLOW} color")

    // Use with enums
    println("Got word: ${getWarmth(Color.YELLOW)} for ${Color.YELLOW} color")

    // Use with objects
    println("Got color: ${mixColors(Color.YELLOW, Color.RED)}")
    println("Got color: ${mixColors(Color.RED, Color.YELLOW)}")
    println("Got color: ${mixColorsOptimized(Color.RED, Color.YELLOW)}")

    // More advance usage
    println("Try to check type: ${checkType(10)}")

    /////////////////////////////////////////
    //// For cycle
    ////////////////////////////////////////

    /*
        Unlike Java, Kotlin doesn't have ordinary for cycle - for (int i=0; i<10; i++) { // do something }
        Kotlin has only one type of for cycle - ranges.
    */

    println("Cycle upward >>")

    // Cycle through the range
    for (i in 1..10) {
        println("Got: $i") // outputs [1, 10] - closed range
    }

    println("Cycle backward >>")

    // Cycle range in reverse order
    for (i in 10 downTo 1 step 2) {
        println("Got: $i")
    }

    println("Open range 1 >>")

    // Cycle range with open range
    val size = 10
    for (x in 0 until size) { // [0, 10) - open range
        println("Got $x")
    }

    println("Open range 2 >>")

    // Equals to this
    for (x in 0 until size) {
        println("Got $x")
    }

    println("If with collections >>")

    // Illustrates how for cycle works with collections
    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'F') {
        val binaryValue = Integer.toBinaryString(c.code)
        binaryReps[c] = binaryValue
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    println("If with collections >>")

    // Simple method how to save index of a collection
    val list = arrayListOf("10", "11", "12")
    for ((index, element) in list.withIndex()) {
        println("$index = $element")
    }

    println("In operator >>")

    val isLetter = isLetter('L')
    val isDigit = !isNotDigit('1')

    println("isLetter = $isLetter isDigit = $isDigit")

    // Any objects can be compared with 'in' operator
    println("Kotlin" in "Java".."Scala")
    // or
    println("Kotlin" in setOf("Java", "Scala"))
}

// Get word associated with color from a phrase - "Каждий охотник желает знать где сидит фазан"
fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Каждый"
        Color.ORANGE -> "Охотник"
        Color.YELLOW -> "Желает"
        Color.GREEN -> "Знать"
        Color.BLUE -> "Где"
        Color.INDIGO -> "Сидит"
        Color.VIOLET -> "Фазан"
    }

// Another example with several values in one branch
fun getWarmth(color: Color) =
    when (color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "Теплый"
        Color.GREEN -> "Нейтральный"
        Color.BLUE, Color.INDIGO, Color.VIOLET -> "Холодный"
    }

// When statement can work with not only constants but also with any objects
fun mixColors(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("Bad color combination")
    }

// Method with better performance than mixColors method
// Instead of setOf() calls we use logic operation as a condition
fun mixColorsOptimized(c1: Color, c2: Color) =
    when {
        ((c1 == Color.RED && c2 == Color.YELLOW) ||
                (c1 == Color.YELLOW && c2 == Color.RED)) -> Color.ORANGE

        ((c1 == Color.YELLOW && c2 == Color.BLUE) ||
                (c1 == Color.BLUE && c2 == Color.YELLOW)) -> Color.GREEN

        ((c1 == Color.BLUE && c2 == Color.VIOLET) ||
                (c1 == Color.VIOLET && c2 == Color.BLUE)) -> Color.INDIGO

        else -> throw Exception("Bad color combination")
    }

// Example of more advance usage
fun checkType(e: Any): String =
    when (e) {
        is Int -> "Integer"
        is String -> "String"
        else -> "Unknown type"
    }

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0'..'9'