/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package design_patterns.publisher_subscriber

import design_patterns.publisher_subscriber.impl.EmailEditor
import design_patterns.publisher_subscriber.impl.NotificationManager

/**
 *  Publisher-Subscriber pattern.
 *
 *  Description:
 *
 *  Email creation application.
 *  It demonstraits the use of Observer Patern when all interested parties are notified about event.
 *
 *  Definition: Define a one-to-many dependency between objects so that when one object
 *  changes state, all its dependents are notified and updated automatically.
 */
fun main() {

    // Create main application class
    val emailEditor = EmailEditor()

    // Subscribe to events in EmailEditor class
    val notificationManager = NotificationManager()
    emailEditor.subscribe(notificationManager)

    // 1. User created email
    emailEditor.createEmail("Hello this is my first email")
    // 2. User sent email
    emailEditor.sentEmail()

    // Email is sent so unsubscribe observers
    emailEditor.unsubscribe(notificationManager)

}