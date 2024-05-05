/*
 * Copyright 2024. Happy coding ! :)
 * Author: Serhii Butryk
 */

package lesson_21_inline_functions

import log
import samples.Person

/**
 * Every time when we create a lambda an instance of a class is created
 * Sometime we want to avoid this. We can use 'inline' feature and compiler will not create a new class
 */

fun main() {

    /**
     * 1. A new object is not created for this lambda
     */
    calculate { print("Hello") }

    /**
     * 2. New object is created for this lambda, because we cannot inline the code here
     */
    val newClass = ClassSavesLambdaInAFiled()
    newClass.calculate { println("Hello") }

    /**
     * 3. Example of local return
     */
    val withAlice = mutableListOf(Person("John"), Person("Alice"), Person("Serhii"))
    findAlice(withAlice)

    /**
     * 4. Example of non-local return
     */
    findAndRemove(withAlice, "Alice")
}

// Inline function
inline fun calculate(action: () -> Unit) {
    // We pass a lambda in other inline function
    // New object is not created here
    otherFunction(action)
}

inline fun otherFunction(action: () -> Unit) {
    action()
}

// Cannot inline
class ClassSavesLambdaInAFiled {

    var action: (() -> Unit)? = null

    // We had to add 'noinline' here, otherwise this will not compile
    inline fun calculate(noinline action: () -> Unit) {
        this.action = action
    }
}

// Local return
fun findAlice(list: List<Person>) {
    list.forEach {
        if (it.name == "Alice") {
            log("Alice is found")
            return
        }
        log("Alice not found")
    }
}

// Non-local return
fun findAndRemove(list: MutableList<Person>, name: String) {
    var found: Person? = null
    list.forEach exit@{
        if (it.name == name) {
            found = it
            return@exit
        }
    }
    list.remove(found)
}

// Or
fun findAndRemove2(list: MutableList<Person>, name: String) {
    var found: Person? = null
    list.forEach {
        if (it.name == name) {
            found = it
            return@forEach
        }
    }
    list.remove(found)
}