/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package design_patterns.bridge.impl

class RemoteStorage : StorageRepository {

    override fun store() {
        println("store(), Save data in remote database")
    }

    override fun toString(): String {
        return "Remote Storage"
    }
}