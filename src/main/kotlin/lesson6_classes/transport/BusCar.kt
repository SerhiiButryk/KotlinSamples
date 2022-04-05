/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson6_classes.transport

/**
 * Example of derived class. Here we can override only 2 methods.
 */
class BusCar : TransportCars() {

    // Overrides method from TransportCars class
    override fun checkEngine() {
        super.checkEngine()
        println("Drive car [BusCar]")
    }

    // Overrides method from Engine interface
    override fun stopCar() {
        super.stopCar()
        println("Drive car [BusCar]")
    }

    // Overrides abstract method
    override fun cleanUp() {
        println("Clean Up")
    }

}