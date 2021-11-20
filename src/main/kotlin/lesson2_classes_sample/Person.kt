/**
 * Copyright 2021. Happy codding ! :)
 * Author: Serhii Butryk
 */
package lesson2_classes_sample

/**
 *  Example of a simple Data class.
 *
 *  Compiler generated functions:
 *  1. equals() / hashCode() pair
 *  2. toString()
 *  3. componentN() corresponding to the properties in their order of declaration
 *  4. copy()
 *
 */

/**
 * Compiler automatically generates fields and their setters and getters if it's needed
 */
class Person(val name: String, var isMarried: Boolean)