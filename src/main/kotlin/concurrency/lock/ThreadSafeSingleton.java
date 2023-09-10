/*
 * Copyright 2023. Happy coding ! :)
 * Author: Serhii Butryk
 */

package concurrency.lock;

/**
 * Correct implementation of thread safe singleton in Java
 */
public class ThreadSafeSingleton {

    private static volatile ThreadSafeSingleton INSTANCE;

    private ThreadSafeSingleton() {}

    public static ThreadSafeSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThreadSafeSingleton();
                }
            }
        }
        return INSTANCE;
    }

}
