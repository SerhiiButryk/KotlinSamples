/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson8_secondary_constructor

/**
 * In Kotlin, interfaces can have declaration of methods and properties,
 * but if properties don't have states.
 *
 * The next interface has 2 properties. One is abstract and it should be overriden in derived classes
 * and other one is not. The value is calculated when the property is read. However, because it is calculated,
 * no states are hold by this interface.
 */
interface User {
    // Abstract property
    val email: String

    // Non-abstract property
    val nickname: String
        get() = email.substringBefore('@')
}