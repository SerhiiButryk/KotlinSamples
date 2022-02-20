/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson6_classes.cars

/**
 * In this example compiler forces us to use 'driveCar' keyword.
 * This happens because both interfaces have same method.
 * Note that we can call base implementation here
 */
class AudiCar : Engine, MyCar {

    override fun driveCar() {
        super<Engine>.driveCar()
        super<MyCar>.driveCar()
        println("driveCar [AudiCar]")
    }

}