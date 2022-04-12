/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package design_patterns.bridge.impl

abstract class BaseRepository(protected var storageRepoImpl: StorageRepository) {

    open fun saveData() {
        storageRepoImpl.store()
    }

}