package algorithms.basic

/**
 * Binary search
 * Complexity - O(log(N))
 */
fun binarySearch(array: Array<Int>, value: Int): Int {

    if (array.isEmpty()) return -1

    var lowerBound = 0
    var upperBound = array.size - 1

    while (true) {

        val mid = (lowerBound + upperBound) / 2

        if (array[mid] == value) {
            return mid
        } else if (lowerBound > upperBound) {
            return -1
        } else {
            if (value > array[mid]) {
                lowerBound = mid + 1
            } else {
                upperBound = mid - 1
            }
        }

    }

}

/**
 * Bubble search
 * Complexity - O(N^2)
 */
fun bubbleSort(array: Array<Int>) {
    for (i in array.lastIndex downTo 0) {
        for (j in 0 ..< i) {
            if (array[j] > array[j + 1])
                swap(array, j, j + 1)
        }
    }
}

/**
 * Selection sort
 *
 * Complexity:
 * 1) Comparisons - O(N^2)
 * 2) Swaps - O(N)
 *
 * It's better in swaps if comparing to bubble sort algorithm.
 * However, the complexity is still O(N^2) for large arrays.
 */
fun selectionSort(array: Array<Int>) {

    for (i in 0..array.lastIndex) {

        var min = i

        // i element selected, check if we have element which is smaller
        for (j in i + 1..array.lastIndex) {
            if (array[j] < array[min])
                min = j
        }

        if (min != i)
            swap(array, min, i)
    }

}

/**
 * Insertion sort
 *
 * Complexity:
 * 1) Worst case - O(N^2)
 * 2) Best case - O(N)
 */
fun insertionSort(array: Array<Int>) {
    for (i in 1..array.lastIndex) {
        var temp = array[i]
        var j = i
        while (j > 0 && array[j - 1] > temp) {
            array[j] = array[j - 1]
            j--
        }
        array[j] = temp
    }
}

/**
 * Swap
 *
 * Complexity - O(1)
 */
fun swap(array: Array<Int>, i: Int, j: Int) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}

/**
 * Sum of digits in a number
 *
 * Complexity - O(log(N))
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

/**
 * Quick sort
 *
 * Complexity - O(N^2)
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