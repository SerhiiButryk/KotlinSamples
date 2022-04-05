/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson6_classes.cars

/**
 * Example of simple interface declaration
 *
 * Methods are abstract or open by default
 */
interface MyCar {
    fun getCarModel() = "My car model"
    fun driveCar() = println("Drive Car [MyCar]")
}