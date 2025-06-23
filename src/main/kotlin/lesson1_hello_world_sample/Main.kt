/**
 * Copyright 2021. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson1_hello_world_sample

/**
 *  Hello world sample.
 *
 *  To compile:
 *  $ kotlinc Sample_1.kt -include-runtime -d app.jar
 *
 *  To run:
 *  $ java -jar app.jar Bob
 */
fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        println("Hi ${args[0]} !")
    }
}