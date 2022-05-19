/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package design_patterns.bridge.impl.storages

class LocalDatabaseStorage : ApplicationStorage {

    override fun store() {
        println("store(), Save data in local database")
    }

    override fun toString(): String {
        return "Local Database Storage"
    }
}