/*
 *  Copyright 2022. Happy coding ! :)
 *  Author: Serhii Butryk
 */
package lesson16_delegation

/**
 * Delegation example
 *
 * 1. Simple property delegation
 * 2. Lazy property delegation
 */

import log
import kotlin.reflect.KProperty

// 1. Simple property delegation

abstract class Driver {
    // Object getter interface
    abstract operator fun getValue(thisRef: Machine, property: KProperty<*>): Driver
    // Object setter interface
    abstract operator fun setValue(thisRef: Machine, property: KProperty<*>, value: Driver)
    abstract fun drive()
}

class CarDriver : Driver() {
    override fun getValue(thisRef: Machine, property: KProperty<*>): Driver {
        log("CarDriver.getValue() {thisRef = $thisRef, property = $property}")
        return this
    }

    override fun setValue(thisRef: Machine, property: KProperty<*>, value: Driver) {
        log("CarDriver.setValue() {thisRef = $thisRef, property = $property, driver = $value}")
        // no-op
    }

    override fun drive() {
        log("CarDriver.drive() driving...")
    }
}

class Machine() {
    // All method calls are called from CarDriver()
    private val driver: Driver by CarDriver()
    fun drive() { driver.drive() }
}

// 2. Lazy property delegation

class Person {
    private val emails by lazy { loadEmailsFromDatabase() }
    // Long operation
    private fun loadEmailsFromDatabase() : List<String> {
        log("Person.loadEmailsFromDatabase() getting emails...")
        return listOf("email1@com", "email2@co'm")
    }
    fun getUserEmails(): List<String> = emails
}

fun main() {

// 1. Simple property delegation

    val machine = Machine()
    machine.drive()

// 2. Lazy property delegation

    val person = Person()
    person.getUserEmails()
}