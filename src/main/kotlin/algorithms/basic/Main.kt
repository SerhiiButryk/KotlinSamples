/*
 * Copyright 2024. Happy coding ! :)
 * Author: Serhii Butryk
 */

package algorithms.basic

/**
 * Basic algorithms
 */

fun main() {

    var num = 0

    // 456
    num += 6
    num += 5 * 10
    num += 4 * 100

    println(num)
}

/**
 * Binary search
 * Array should be sorted
 */
fun binarySearch(arr: IntArray, value: Int): Int {

    var start = 0
    var end = arr.size - 1

    while (start <= end) {
        val middle = start + (end - start) / 2

        if (value > arr[middle]) {
            start = middle + 1
        } else if (value < arr[middle]) {
            end = middle - 1
        } else {
            return middle
        }
    }

    return -1
}


/**
 * Bubble sort
 * Worst case: O(N^2)
 */
fun bubbleSort(arr: IntArray) {
    for (i in arr.indices) {
        // When the inner cycle is done, one element in the end is sorted,
        // and we can do fewer steps in the next iteration.
        // That's why we do minus i steps here.
        for (j in 0 until arr.size - 1 - i) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}

/**
 * Quick sort
 * Worst case: O(N^2)
 * https://www.youtube.com/watch?v=Vtckgz38QHs
 */
fun quickSort(arr: IntArray, start: Int, end: Int) {

    if (start >= end)
        return

    val pivot = findPivot(arr, start, end)
    quickSort(arr, start, pivot - 1)
    quickSort(arr, pivot + 1, end)
}

fun findPivot(arr: IntArray, start: Int, end: Int): Int {

    // Two indices, one is at the beginning, the second is before the beginning
    var i = start - 1
    var j = start

    // Select pivot at the end
    val pIndex = end

    while (j < end) {

        if (arr[j] < arr[pIndex]) {
            ++i
            // Swap element at i with element at j
            val temp = arr[j]
            arr[j] = arr[i]
            arr[i] = temp
        }

        ++j
    }

    ++i

    // Swap to place the pivot at the correct place
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp

    return i
}

/**
 * Sum of digits in a number
 */
fun sum(numb: Int): Int {

    var sum = 0
    var num = numb

    while (num != 0) {
        sum += num % 10
        num /= 10
    }

    return sum
}