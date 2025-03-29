/*
 * Copyright 2024. Happy coding ! :)
 * Author: Serhii Butryk
 */

package algorithms.task1

fun main() {

}

// Find a 0-1 pair in a sequence with 0 and 1 numbers with few attempts
// Example of sequence:
// | 0 |  |  |  |  |  |  |  | ... | 1 |
// The first elem should be '0' and the last elem should be '1'
fun find01Pairs(arr: Array<Int>): Int { // Complexity - O(log(n))

    // Don't check empty array
    if (arr.isEmpty()) {
        return -1
    }

    // Don't check array with 1 value
    // 0 or 1 - not a valid pair
    if (arr.size == 1) {
        return -1
    }

    // Don't check array if size is 2 and values are equal
    // 0, 0 or 1, 1 - not a valid pair
    if (arr.size == 2 && arr.first() == arr.last()) {
        return -1
    }

    var start = 0
    var end = arr.size - 1

    // Do not check such sequence
    if (arr[start] != 0 || arr[end] != 1) {
        return -1
    }

    var interNumb = 0

    while (end > start + 1) { // While diff between values more than 1

        println("Started iteration: ${interNumb++}")

        val pointer = (start + end) / 2

        if (arr[pointer] == 1) {
            // Switch to left
            // | 0 |  |  |  | 1 |  |  |  | ... | 1 |
            end = pointer
        } else {
            // Switch to right
            // | 0 |  |  |  | 0 |  |  |  | ... | 1 |
            start = pointer
        }

    }

    println("Done")

    return start
}