/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson10_lambda_expressions

/**
 * Lambda functions.
 *
 * Lambda functions are good because:
 * 1. They simplify work with collections.
 * 2. They make code more understandable.
 * 3. They help to get rid of code dublication.
 *
 * Syntax: (x: Int, y: Int) -> x + y
 *
 * Features:
 * 1. Can be passed as an argument to a function.
 * 2. Can be saved to local variable.
 * 3. Can access and modify local variables.
 */

data class Person(val name: String, val age: Int)

fun main() {

    /**
     * 1. Find the largest element in the list with lambda.
     */
    val persons = listOf(Person("John", 29), Person("Tom", 31))
    println(persons.maxByOrNull { it.age })

    /**
     * 2. Print people names with lambda.
     */
    val names = persons.joinToString (separator = " ",  transform = { p: Person -> p.name })
    println(names)
    // Lambda function can be moved out of parentethis
    val names2 = persons.joinToString (separator = " ") { p: Person -> p.name }
    println(names2)

    /**
     * 3. Different lambda function declarations.
     */
    persons.maxByOrNull { p: Person -> p.age } // Type is specified explicitly
    persons.maxByOrNull { p -> p.age } // Type is deduced from the context
    // Use default argument. The 'it' argument is available if lambda function has 1 parameter
    persons.maxByOrNull { it.age }

    /**
     * 4. Access and modify local variables.
     */
    var clientErrors = 0
    var serverErrors = 0

    // Imaginary collection with http status responses
    val serverResponses = listOf("500 Internal Server Error", "404 Not Found")

    // Local function.
    fun printProblemCounts() {
        // Iterate throught eche http status response
        serverResponses.forEach { element ->
            if (element.startsWith("4")) {
                clientErrors++
            } else if (element.startsWith("5")) {
                serverErrors++
            }
        }
    }

    printProblemCounts()
    println("Client errors: $clientErrors, server errors: $serverErrors")

    /**
     * 5. References to methods and class members.
     */
    fun sayHello() = println("Hello")
    // You can call local function using run function.
    run(::sayHello)

    fun sendEmail(person: Person, message: String) {/* some code here */}
    val action = { person: Person, message: String ->
        sendEmail(person, message)
    }
    // Reference to local function
    val nextAction = ::sendEmail

    // Reference to constructor
    val createPerson = ::Person
    val p = createPerson("Alice", 29)
    println(p)
}