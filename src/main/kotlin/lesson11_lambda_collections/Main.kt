/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson11_lambda_collections

import lesson10_lambda_expressions.Person

/**
 * Using Lambda functions in Collections
 *
 * 1. Map - modifying every element in collection
 * 2. Filter - remove element in collection if true, add element to new collection if false
 */

fun main() {

    // 1. Map - modifying every element in collection
    val collection1 = listOf(1, 2, 3, 4, 5)
    // Change collection
    val newCollection = collection1.map { element -> element * element }
    println("Old collection: $collection1")
    println("New collection: $newCollection")

    // 2. Filter - remove element in collection if true, add element to new collection if false
    val collection2 = listOf(Person("Tom", 12), Person("John", 23), Person("Mike", 19))

    // Find Persons which are older than 18 years
    val fullYearsPersons = collection2.filter { person -> person.age >= 18 }
    println("Full years person: $fullYearsPersons")

}