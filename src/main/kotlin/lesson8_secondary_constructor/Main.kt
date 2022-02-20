/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson8_secondary_constructor

/**
 * 1. Different ways of implementing properties
 */

// Property is explicitly overriden by derived class
class PrivateUser(override val email: String) : User

class SubscribeUser(val userId: String) : User {
    // Suppose 'getUserEmail' method can perform expencive database or network call
    override val email: String = getUserEmail(userId)

    private fun getUserEmail(userId: String): String = "my user email"
}

/**
 * 2. Accessing filed from 'set' method
 */
class MyUser(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println(
                """
            Address was changed for $name:
            "$field" -> "$value"
        """.trimIndent()
            )
            field = value
        }
}

/**
 * 3. Changing visibility of 'set' method
 */
class LengthCounter {
    // This property cannot be modified from outside the class
    var counter: Int = 0
        private set

    fun increaseCounter() {
        counter++
    }
}

/**
 * 4. Equal operation
 *
 * In Kotlin, '==' operator invodes 'equals' method and '===' operator actually compares object referencese.
 * Always implement both of 'hashcode' and 'equals' method. This eliminates possible mistakes.
 */

/**
 * 5. By operator
 *
 * In Kotlin, decorator pattern is supported natively by the language.
 * The next example demonstrats how to implement decorator around collection in Kotlin.
 *
 * The next class overrides 2 methods 'add' and 'addAll'. All other calls are delegated to collection object.
 */
class CountingSet<T>(
    val innerSet: MutableCollection<T>
) : MutableCollection<T> by innerSet {

    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }

}

fun main() {

}
