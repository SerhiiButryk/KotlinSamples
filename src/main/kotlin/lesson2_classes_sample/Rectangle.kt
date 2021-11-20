/**
 * Copyright 2021. Happy codding ! :)
 * Author: Serhii Butryk
 */
package lesson2_classes_sample

/**
 * Compiler automatically generates fields and their setters and getters if it's needed
 */
class Rectangle(val height: Int, val width: Int) {

    /** Property which doesn't contain data.
     * There is no need to define property for holding
     * information whether rectangle is square or not as
     * we can dynamically calculate the value of it.
     **/
    val isSquare: Boolean
    get() {
        return height == width
    }

}