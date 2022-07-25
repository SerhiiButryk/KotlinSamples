/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson12_regular_expressions

/**
 * Regular expressions in Kotlin.
 *
 * Kotlin adds new operator for defining regular expressions.
 * It is triple quotes - """ """. Here are the examples of work with regular expressions.
 */
fun main() {

    val pattern1 = "\\.|-"
    val text = "12.345-6.A"

    val result = text.split(pattern1.toRegex())
    println(result)

    val result2 = text.split(".", "-")
    println(result2)

    val path = "/Users/myname/desktop/file/my.doc"

    // Do not need to escape special characters with this syntax
    val regex = """(.+)/(.+)\.(.+)""".toRegex()

    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Directory: $directory, file name: $filename, extension: $extension")
    }

}