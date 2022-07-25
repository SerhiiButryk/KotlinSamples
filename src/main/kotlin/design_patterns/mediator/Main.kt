/*
 *  Copyright 2022. Happy coding ! :)
 *  Author: Serhii Butryk
 */

package design_patterns.mediator

/**
 * Mediator pattern
 *
 * Description:
 * If you have a lot of classes which depends on each other, it's hard to reuse them.
 *
 * Better if you create a controller(mediator) class and allows them to talk to it.
 * Classes should talk to mediator class instead of communication to each other.
 *
 * In other words, they shouldn't know about other classes only about mediator class.
 * Meanwhile, mediator knows about every class and controls communication between them.
 *
 * In this way you have benefits:
 * 1. You can reuse code
 * 2. You can add new mediator classes
 * 3. You can reduce dependencies and prepare code for modularization.
 *
 * Definition: Reduce chaotic dependencies between objects.
 */
fun main() {

}