/*
 *  Copyright 2022. Happy coding ! :)
 *  Author: Serhii Butryk
 */

package design_patterns.states.impl.states

import design_patterns.states.impl.TCPClient

/**
 * Represents TCP closed state
 */
class TCPClosed(client: TCPClient) : TCPState(ConnectionState.CLOSED, client) {

}