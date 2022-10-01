/*
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */

package lesson17_operators

/**
 * Operators in Kotlin.
 * -------------------------
 * Binary operators:
 * -------------------------
 * |  a * b   |  times  |
 * |  a / b   |  div    |
 * |  a % b   |  mod    |
 * |  a + b   |  plus   |
 * |  a - b   |  minus  |
 * --------------------------
 * |  *=   |  timesAssign  |
 * |  /=   |  divAssign    |
 * |  %=   |  modAssign    |
 * |  +=   |  plusAssign   |
 * |  -=   |  minusAssign  |
 * ---------------------------
 * Bitwise operators
 *  | shl   | << n
 *  | shr   | >> n
 *  | ushr  |
 *  | and   | n && n
 *  | or    | n || n
 *  | xor   | ^n
 *  | inv   | ~n
 *  -----------------------------
 *  Unary operators
 *  | +n         |  unaryPlus   |
 *  | -n         |  unaryMinus  |
 *  | !n         |  not         |
 *  | ++n, n++   |  inc         |
 *  | --n, n--   |  dec         |
 *  ------------------------------
 *
 *  Notes:
 *  1) Operator '==' invokes equals() function
 *  2) Operator '!==' invokes equals() function and inverts returned result
 *  3) Operator '>, <, >=, <=' invokes compareTo() function
 *  4) ++n and ++n with user defined operators works the same as with primitive types.
 *  Compiler invokes postfix or prefix functions if appropriate
 *  5) You should never implement plus() and plusAssign() operators together. It creates ambiguity.
 *  You should choose ether one or other operator.
 */

class Number(var number: Int) {
    override fun toString(): String {
        return number.toString()
    }
}

/**
 * '+' operator
 */
operator fun Number.plus(other: Number): Number {
    return Number(this.number + other.number)
}

/**
 * '*' operator
 *
 * Parameters and function return type can be different.
 * For example, the next code will return string and it accepts char
 */
operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}

fun main() {
    val number1 = Number(1)
    val number2 = Number(1)
    var result = number1 + number2
    println(" $number1 + $number2 = $result")
}