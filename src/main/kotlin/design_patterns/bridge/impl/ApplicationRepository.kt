/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package design_patterns.bridge.impl

class ApplicationRepository : BaseRepository(LocalDatabaseStorage()) {

    fun setMainStorage(storageRepo: StorageRepository) {
        storageRepoImpl = storageRepo
    }

    override fun saveData() {
        println("saveData(), Save application data to $storageRepoImpl")
        super.saveData()
        println("saveData(), Data is saved")
    }

}