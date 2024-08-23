/**
 * Copyright 2021. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson2_classes_sample

/**
 * Example of a simple class
 * Compiler automatically generates setters and getters for fields if they are needed
 */
class Rectangle(private val height: Int, private val width: Int) {

    /** Property which doesn't contain data.
     * There is no need to define property for holding
     * information whether rectangle is square or not as
     * we can dynamically calculate this value.
     **/
    val isSquare: Boolean
        get() {
            return height == width
        }

}