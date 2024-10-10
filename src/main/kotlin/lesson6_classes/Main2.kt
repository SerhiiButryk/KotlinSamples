/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson6_classes

/**
 * Example demonstrates class initialization order
 */

class Derived : Base {

    init {
        println("Init block called of Derived class called")
    }

    val b = DifferentClass()

    constructor(number: Int) {
        println("Derived(Int) called")
    }

    override fun doSomething() {
        println("Derived::doSomething() called")
    }
}

class DifferentClass {
    constructor() {
        println("DifferentClass() called")
    }
}

open class Base {
    constructor() {
        println("Base() called")
    }

    open fun doSomething() {
        println("Base::doSomething() called")
    }
}

fun main() {
    // Create an objects
    val a = Derived(10)
    a.doSomething()
}