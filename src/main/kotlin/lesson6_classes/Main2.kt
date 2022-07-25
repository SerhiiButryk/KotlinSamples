/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson6_classes

/**
 * Example demonstrates class initialization order
 */

class A : D {

    val b = B()

    init {
        println("Init block called of A class called")
    }

    constructor(number: Int) {
        println("A(Int) called")
    }

    override fun doSomething() {
        println("A::doSomething() called")
    }
}

class B {
    constructor() {
        println("B() called")
    }
}

open class D {
    constructor() {
        println("D() called")
    }

    open fun doSomething() {
        println("D::doSomething() called")
    }
}

fun main() {

    // Create objects
    val a = A(10)
    a.doSomething()

}