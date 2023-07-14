/**
 * Copyright 2021. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson2_classes_sample

/**
 * Lesson 1. Properties
 *
 * Properties of classes are fields along with setter and getter methods.
 * Properties in Kotlin can be of 2 types: val and var.
 *
 * For accessing a field Kotlin provides 'field' keyword:
 *  set(value) {
 *      field = value
 *  }
 *  get() = field
 *
 *  A backing field is generated if a property used at least one default accessor (setter or getter)
 *  or custom setter or getter uses 'field' keyword.
 *
 * Lesson 2. Classes
 *
 *  Classes in Kotlin can have:
 *  1. Object declaration
 *  2. Property declaration
 *  3. Constructors and init blocks
 *  4. Functions
 *  5. Nested and inner classes
 *
 *  Nested classes don't have a ref to outer class.
 *  Inner classes have a ref to outer class.
 *
 *  Lesson 3. Packages
 *
 *  Kotlin doesn't have any restrictions on packages and files.
 *  In Kotlin, file doesn't need to have the same name as the class
 *  which it contains. Moreover, it allows to place several classes in one file
 *  and arbitrary name it.
 *
 */
fun main() {
    // Create object
    val person = Person("John", false)

    // Access properties
    println("Name: ${person.name}")
    println("Married: ${person.isMarried}")

    // Modify property
    person.isMarried = true
    println("Married: ${person.isMarried}")

    // Class with custom getter
    val rectangle = Rectangle(100, 100)
    println("Is square: ${rectangle.isSquare}")
}