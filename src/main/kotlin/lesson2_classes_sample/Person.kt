/**
 * Copyright 2021. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson2_classes_sample

/**
 *  Example of a simple Data class.
 *
 *  Compiler generates functions:
 *  1. equals() / hashCode() pair
 *  2. toString()
 *  3. componentN() corresponding to the properties in their order of declaration
 *  4. copy()
 *
 *  Restrictions:
 *
 *  1. Data classes can inherit abstract classes, other classes and implement interface, but
 *  it can't inherit other Data classes (open keyword is NOT allowed on Data classes)
 *
 *  2. varargs are not allowed (due to some JVM limitations)
 *
 */

/**
 * Compiler automatically generates fields and their setters and getters if it's needed
 */
data class Person(val name: String, var isMarried: Boolean = false)