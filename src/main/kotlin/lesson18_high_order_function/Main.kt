/*
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson18_high_order_function

fun main() {

    /**
     * Lambda function with a receiver
     */
    val repeatFunc: String.(Int) -> String = {
        // this is a ref to String object
        this.repeat(it)
    }

    /**
     * Lambda function without a receiver
     */
    val repeatFunction2: (String, Int) -> String = repeatFunc

    /**
     * Anonymous function
     */
    val repeatFunction3 = fun(a: String, b: Int): String = a.repeat(b)

    // Invocation
    println("hello".repeatFunc(2))
    println(repeatFunction2("hello", 2))
    println(repeatFunction3("hello", 2))
}



