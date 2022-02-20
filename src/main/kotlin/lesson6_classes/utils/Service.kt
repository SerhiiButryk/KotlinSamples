/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson6_classes.utils

class Service {

    fun performService(serviceName: String) {
        println("performService, $serviceName")
    }

    // This class is analog to private static class in Java
    class ServiceHelper {
        fun lookUpService(service: String) {}
    }

    // This class has reference to outer class
    inner class ServiceHandler {
        fun handle() {
            // Can access method from outer class
            performService("")
            // Or using reference to outer class
            this@Service.performService("")
        }
    }

}