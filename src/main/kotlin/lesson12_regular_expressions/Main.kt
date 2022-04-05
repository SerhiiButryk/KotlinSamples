/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson12_regular_expressions

/**
 * Regular expresions in Kotlin.
 *
 * Kotlin adds new operator for defining regular expresions.
 * It is tripple quotes - """ """. Here are the examples of work with regular expresions.
 */
fun main() {

    val pattern1 = "\\.|-"
    val text = "12.345-6.A"

    val result = text.split(pattern1.toRegex())
    println(result)

    val result2 = text.split(".", "-")
    println(result2)

    val path = "/Users/myname/desktop/file/my.doc"

    // Do not need to escale special chanracters with this syntax
    val regex = """(.+)/(.+)\.(.+)""".toRegex()

    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Directory: $directory, file name: $filename, extension: $extension")
    }

}