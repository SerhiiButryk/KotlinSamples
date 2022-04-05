/**
 * Copyright 2021. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson1_hello_world_sample

/**
 *  Basic program which demonstrates basic syntax of the language.
 *
 *  Compile:
 *  $ kotlinc Main.kt -include-runtime -d app.jar
 *  Run:
 *  $ java -jar app.jar Bob
 */
fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        println("Hi ${args[0]} !")
    }
}