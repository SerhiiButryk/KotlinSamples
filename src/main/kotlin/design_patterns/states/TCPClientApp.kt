/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package design_patterns.states

import design_patterns.states.impl.TCPClient

/**
 * State pattern.
 *
 * Program: TCP client application.
 *
 * Description:
 *
 * Let's assume that we have one giant class with internal state.
 * When state is changed, class should change the behavior. Code is difficult to support
 * and extend, because it's hard to understand all places where class behaviors differently.
 *
 * It better to create a class for each state, move common code to base class and
 * code which depends on state to subclasses. In this case, we can change the code
 * without a fear of breaking functionality, because code is isolated in different classes.
 *
 * Definition: Let an object alter its behavior when its internal state changes.
 * It appears as if the object changed its class.
 *
 */
fun main() {

    // 1. Create application class
    val tcpClient = TCPClient()

    // 2. Call APIs
    tcpClient.establish()
    tcpClient.connect()
    tcpClient.listen()
    tcpClient.close()

    // Instead of one class, there are several smaller classes
    // which handles states and conditions.
    // This can reduce complexity and increase maintainability of the code.
    // However, this pattern is not 100 % correct for all situations.
    // So, use it if it really has benefits.

}