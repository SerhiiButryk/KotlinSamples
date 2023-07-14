/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson8_secondary_constructor

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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
 * In Kotlin, '==' operator invokes 'equals' method and '===' operator actually compares object referencese.
 * Always implement both of 'hashcode' and 'equals' method. This avoids possible mistakes.
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

/**
 * Properties can be decorated as well using by keyword. This can be useful when you need
 * to reuse getter/setter logic.
 *
 * The next example demonstrates this feature.
 */

// Suppose we have class Person. We need to add formatting
// to 'name' and 'lastname' fields. We can do this using setter/getter.
// However, logic will be duplicated if we don't use delegation.
// Property delegation resolves this issue.
class Person(name: String, lastname: String) {
    var name: String by FormatDelegate()
    var lastname: String by FormatDelegate()
}

class FormatDelegate : ReadWriteProperty<Any?, String> {
    private var formattedString: String = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return formattedString
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        formattedString = value.lowercase().replaceFirstChar { it.uppercase() }
    }
}

/**
 * 6. The order of initialization
 *
 * 1. Child constructor arguments
 * 2. Parent constructor arguments
 * 2. Parent properties and init blocks
 * 3. Parent constructor body
 * 4. Child properties and init blocks
 * 5. Child constructor body
 */

open class Parent {

    private val prop1 = println("Parent.prop1")

    constructor(arg: Unit = println("Parent constructor arg")) {
        println("Parent(int) {...}")
    }

    init {
        println("Parent init block")
    }

    private val prop2 = println("Parent.prop2")
}

class Child : Parent {

    private val prop1 = println("Child.prop1")

    constructor(arg: Unit = println("Child secondary constructor arg")) : super() {
        println("Child() {...}")
    }

    constructor(value: Int, arg: Unit = println("Child primary constructor arg")) : this(arg) {
        println("Child(int) {...}")
    }

    init {
        println("Child init block")
    }

    private val prop2 = println("Child.prop2")
}

fun main() {

    val child = Child(1)

}
