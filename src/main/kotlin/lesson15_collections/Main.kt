/*
 *  Copyright 2022. Happy coding ! :)
 *  Author: Serhii Butryk
 */

package lesson15_collections

/**
 * Collections in Kotlin
 *
 * In Kotlin collections can be
 * 1) Mutable or immutable
 * 3) Nullable or not nullable (can or cannot have null elements)
 *
 * Note that collections which are passed from Java have special PLATFORM TYPE.
 *
 * There are 3 main collection:
 * 1) Set
 * 2) List
 * 2) Map
 *
 * Collection can be created by the next methods:
 *
 *          |        read only  |   read and write
 * -----------------------------------------------------------------------------------
 * List     |       listOf      |   mutableListOf, arrayListOf
 * Set      |       setOf       |   mutableSetOf, hashSetOf, linkedSetOf, sortedSetOf
 * Map      |       mapOf       |   mutableMapOf, hashMapOf, linkedMapOf, sortedMapOf
 * -----------------------------------------------------------------------------------
 */

fun main() {

    //////////////////////////////////////////////////////
    // Combine elements from several list collections in single one
    // flatten()
    //////////////////////////////////////////////////////

    val list = listOf(listOf(1, 2, 3, 4), listOf(1, 2, 3, 4))

    val newList = list.flatten()

    // The result is a new collection of all elements from list collections
    println(newList)

    //////////////////////////////////////////////////////
    // Transform and combine elements from several list collections in single one
    // flatten()
    //////////////////////////////////////////////////////

    val list2 = listOf(Person("John", listOf("252-233-23256", "3736-3839-3939")),
        Person("John", listOf("40-24-433-24o43256", "400-4949-5059")))

    val newListWithAllPhoneNumbers = list2.flatMap { it.phones }

    println(newListWithAllPhoneNumbers)
}

class Person(val name: String, val phones: List<String>)