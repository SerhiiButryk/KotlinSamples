/*
 *  Copyright 2022. Happy coding ! :)
 *  Author: Serhii Butryk
 */
package topics.delegation_example

import util.log
import kotlin.reflect.KProperty

/**
 * Delegation example.
 */
abstract class Observer {
    abstract fun <T: Any> onChange(fieldName: String, oldValue: T, newValue: T)
}

class ObservableProperty<T: Any, V: Any>(private var propertyValue: V, private val observer: Observer) {

    operator fun getValue(observableObject: T, property: KProperty<*>): V {
        log("ObservableProperty.getValue()")
        return propertyValue
    }

    operator fun setValue(observableObject: T, property: KProperty<*>, newValue: V) {
        log("ObservableProperty.setValue()")
        val oldValue = propertyValue
        propertyValue = newValue
        observer.onChange(property.name, oldValue, newValue)
    }

}

class PersonObserver<T> : Observer() {
    override fun <T : Any> onChange(fieldName: String, oldValue: T, newValue: T) {
        log("PersonObserver.onChange(), $fieldName, $oldValue, $newValue")
    }
}

class Person(val name: String, age: Int, salary: Int) {
    var salary: Int by ObservableProperty<Person, Int>(salary, PersonObserver<Int>())
    var age: Int by ObservableProperty<Person, Int>(age, PersonObserver<Int>())
    fun print() {
        log("$name , $salary , $age")
    }
}

fun main(args: Array<String>) {
    val person = Person("John", 25, 100)
    // get values, calls getValue()
    person.print()

    // set values, calls setValue()
    person.age = 33
    person.salary = 200
}