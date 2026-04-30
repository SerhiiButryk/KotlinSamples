package algorithms

// Find the first (0,1) pair in a sequence of 0 and 1 values with few attempts
// Example sequence:
// | 0 |  |  |  |  |  |  |  | ... | 1 |
// The first element should be '0' and the last element should be '1'.
// Uses a binary search method.
fun find01Pairs(arr: Array<Int>): Int { // Complexity - O(log(n))

    // Do not check an empty array
    if (arr.isEmpty()) {
        return -1
    }

    // Do not check an array with one value
    // 0 or 1 is not a valid pair
    if (arr.size == 1) {
        return -1
    }

    // Do not check arrays of size 2 with equal values
    // 0, 0 or 1, 1 is not a valid pair
    if (arr.size == 2 && arr.first() == arr.last()) {
        return -1
    }

    var start = 0
    var end = arr.size - 1

    // Do not check sequences like this
    if (arr[start] != 0 || arr[end] != 1) {
        return -1
    }

    var interNumb = 0

    while (end > start + 1) { // While the difference between values is more than 1

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