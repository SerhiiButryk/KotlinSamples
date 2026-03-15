package algorithms.basic

/**
 * Stack demo examples
 */

fun main() {
    println(makeParenthesisCheck("c[d]")) // True
    println(makeParenthesisCheck("a{b[c]d}e")) // True
    println(makeParenthesisCheck("a{b(c]d}e")) // False
    println(makeParenthesisCheck("a[b{c}d]e}")) // False
    println(makeParenthesisCheck("a{b(c)")) // False
}

fun makeParenthesisCheck(test: String): Boolean {

    val stack = MyStack(20)

    for (char in test) {

        if (char == '[' || char == '{' || char == '(' ) {

            stack.push(char)

        } else if (char == ']' || char == '}' || char == ')') {

            if (stack.isEmpty()) return false

            val lastChar = stack.pop()

            if (!parenthesesMatches(open = lastChar, close = char)) {
                return false
            }

        }

    }

    return stack.isEmpty()

}

fun parenthesesMatches(open: Char, close: Char): Boolean {
    return when (open) {
        '(' if close == ')' -> {
            true
        }
        '[' if close == ']' -> {
            true
        }
        '{' if close == '}' -> {
            true
        }
        else -> {
            false
        }
    }
}

class MyStack(size: Int) {

    private val arr = Array(size) { ' ' }
    private var top = -1

    fun push(value: Char) {
        arr[++top] = value
    }

    fun pop() = arr[top--]

    fun peek() = arr[top]

    fun isEmpty() = top == -1
    fun isFull() = top == arr.lastIndex
}
