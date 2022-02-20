/**
 * Copyright 2021. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson1_hello_world_sample

/**
 *  Simple kotlin program. Demonstrates kotlin basic syntax.
 *
 *  Compile and run kotlin:
 *  $ kotlinc Main.kt -include-runtime -d app.jar
 *  $ java -jar app.jar Bob
 */
fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        println("Hi ${args[0]} !")
    }
}