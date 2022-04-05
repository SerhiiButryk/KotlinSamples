/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson6_classes

import lesson6_classes.cars.AudiCar
import lesson6_classes.transport.BusCar
import lesson6_classes.utils.Service

/**
 * Classes in Kotlin.
 *
 * 1. All declarations are 'final' and 'public' by default.
 * By setting 'final' modifier, Kotlin tries to resolve 'fragile class' issue, which is a problem in Java.
 * See - https://en.wikipedia.org/wiki/Fragile_base_class
 *
 * 2. Nested classes doesn't have reference to outer class.
 *
 * 3. Interfaces can have concrete and abstract methods, but can't have states.
 * However, they can have properties, but they should be abstract or have an  accessor implementations.
 * You must explicitly mark methods that you want to override as 'override'.
 * This ensures that you don't override method of base class by mistake.
 *
 * 4. Casting works only if classes or properties are not changed in subclasses.
 *
 * 5. Visibilities modifiers:
 * Public Protected Private Internal
 *
 * 6. Kotlin doesn't have namespaces
 *
 * 7. Sealed class - classes that have restrictions for subclassing.
 * Sealed classes doen't have children outside the class declaration.
 * For example, the next class declarations says that Expr can have
 * only 2 subclasses (Num and Sum)
 *
 * seald class Expr {
 *     class Num(val value: Int) : Expr()
 *     class Sum(val left: Expr, val right: Expr) : Expr()
 * }
 */
fun main() {
    // 1. Interfaces
    val audiCar = AudiCar()
    audiCar.driveCar()

    // 2. Inner and nested classes
    val service = Service()
    service.performService("")

    // 3. Inheritance
    val bus = BusCar()
}