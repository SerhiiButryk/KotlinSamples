/*
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson18_high_order_function

/**
 * 1. Lambda function with a receiver
 * 2. Lambda function without a receiver
 * 3. Anonymous function
 * 4. Non local return from lambda function
 * 5. Local return from lambda function
 */

fun main() {

    /**
     * 1. Lambda function with a receiver
     */
    val repeatFunc: String.(Int) -> String = {
        // this is a ref to String object
        this.repeat(it)
    }

    /**
     * 2. Lambda function without a receiver
     */
    val repeatFunction2: (String, Int) -> String = repeatFunc

    /**
     * 3. Anonymous function
     */
    val repeatFunction3 = fun(a: String, b: Int): String = a.repeat(b)

    // Invocation
    println("hello".repeatFunc(2))
    println(repeatFunction2("hello", 2))
    println(repeatFunction3("hello", 2))

    /**
     * 4. Non local return from lambda function
     */
//    val res = lookForAlice(listOf("Tom", "Alice", "Taras"))
//    val res = lookForAlice2(listOf("Tom", "Alice", "Taras", "Sem", "John"))
    val res = lookForAlice3(listOf("Tom", "Alice", "Taras", "Sem", "John"))
    println(res)
}

fun lookForAlice(names: List<String>): Boolean {
    names.forEach { name ->
        if (name == "Alice") {
            // Alice is found !
            return true
        }
    }
    // Alice in not found
    return false
}

fun lookForAlice2(names: List<String>): Boolean {
    names.forEach local_return@ { name ->
        println("In lambda function")
        if (name == "Alice") return@local_return
    }
    println("Out from lambda function !")
    // Alice in not found
    return false
}

// anonymous function
fun lookForAlice3(names: List<String>): Boolean {
    names.forEach (fun (name) {
        println("In anonymous function")
        if (name == "Alice") return
    })
    println("Out from lambda function !")
    // Alice in not found
    return false
}
