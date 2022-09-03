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

import util.log
import kotlin.reflect.KProperty

// 1. Simple property delegation

abstract class Driver {
    abstract operator fun getValue(machine: Machine, property: KProperty<*>): Driver
    abstract operator fun setValue(machine: Machine, property: KProperty<*>, driver: Driver)
    abstract fun drive()
}

class CarDriver : Driver() {
    override fun getValue(machine: Machine, property: KProperty<*>): Driver {
        log("CarDriver.getValue() {machine = $machine, property = $property}")
        return this
    }

    override fun setValue(machine: Machine, property: KProperty<*>, driver: Driver) {
        log("CarDriver.setValue() {machine = $machine, property = $property, driver = $driver}")
        // no-op
    }

    override fun drive() {
        log("CarDriver.drive() driving...")
    }
}

class Machine() {
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