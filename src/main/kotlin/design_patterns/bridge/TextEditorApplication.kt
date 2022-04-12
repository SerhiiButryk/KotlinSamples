/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package design_patterns.bridge

import design_patterns.bridge.impl.ApplicationRepository
import design_patterns.bridge.impl.RemoteStorage

/**
 * Bridge pattern
 *
 * Text editor application.
 *
 *  Description:
 *
 * Let's say that we have complex text editor application.
 * We want to save our aditor settings, extention settings and created files in Database.
 *
 * We can create a class for doing this. However, what if later we need to save this information
 * in other Database or event in Remote Database. We are obliged to add new classes with different interfaces.
 * This is not quite flexible solution.
 *
 * Bridge pattern allows to solve this problem easy.
 *
 * We can create 2 parallel hierarchy for implementation and for abstraction of Databases.
 * Then we can reuse iterfaces and implementation.
 *
 * Definition: Decouple the functional abstraction from the implementation so that the two can vary independently
 */
fun main() {

    // 1. Create our application classes
    val applicationRepository = ApplicationRepository()
    // a lot of other classes

    // 2. Working with our application ...

    // 3. It's time to save data

    // By default saving into local storage
    applicationRepository.saveData()

    // Save data to remote storage
    applicationRepository.setMainStorage(RemoteStorage())
    applicationRepository.saveData()

    // Now you can see that can easy work with different storages using single interface.
    // Of couse, this can grow and be modified. Interfaces can be extended and new database
    // impls could be added.
}
