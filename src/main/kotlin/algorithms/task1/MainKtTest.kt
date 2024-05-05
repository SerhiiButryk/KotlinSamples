/*
 * Copyright 2024. Happy coding ! :)
 * Author: Serhii Butryk
 */

package algorithms.task1

import org.junit.jupiter.api.Test

class MainKtTest {

    @Test
    fun test01_find01Pairs() {

        val arr = arrayOf(0, 1, 0, 0, 1, 0, 0, 0, 1, 1) // 10

        val index = find01Pairs(arr)

        println("Index = $index, pair = {${arr[index]}, ${arr[index + 1]}}")
    }

    @Test
    fun test02_find01Pairs() {

        val arr = arrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1) // 10

        val index = find01Pairs(arr)

        println("Index = $index, pair = {${arr[index]}, ${arr[index + 1]}}")
    }

    @Test
    fun test03_find01Pairs() {

        val arr = arrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0,
            1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1) // 30

        val index = find01Pairs(arr)

        println("Index = $index, pair = {${arr[index]}, ${arr[index + 1]}}")
    }
}