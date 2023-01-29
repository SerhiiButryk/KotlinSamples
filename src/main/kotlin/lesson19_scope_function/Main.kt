/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package lesson19_scope_function

class Person() {
    var name: String = ""
    var age: Int = 0
}

fun main() {

    val per = Person().apply {
        name = "john"
    }

    println(per.name)

    val per2 = Person().also {
        it.name = "john"
    }

    println(per2.name)

    with(per) {
        name = "tom"
    }

    println(per.name)

    val fun2:() -> Unit = { run { println("hello") } }

    doSmth(fun2)

    val name: String? = null

    name?.run { length }

    name?.let {
        println(it.length)
    }

}

fun doSmth(action: () -> Unit) {
    action()
}