/**
 * Copyright 2022. Happy coding ! :)
 * Author: Serhii Butryk
 */
package lesson8_secondary_constructor

class Context

/**
 * 1. Class which declares no primary constructor and 2 secondary constructors
 */
open class View {
    constructor(ctx: Context) {
        println("Secondary constructor with 1 param")
    }

    constructor(ctx: Context, style: Int) {
        println("Secondary constructor with 2 param")
    }
}

/**
 * 2. Class which calls constructors of base class and delegates call from one constructor to another
 * using 'this' keyword
 */
class MyView : View {

    constructor(ctx: Context) : this(ctx, MY_STYLE) {
        println("Secondary constructor with 1 param")
    }

    constructor(ctx: Context, style: Int) : super(ctx, style) {
        println("Secondary constructor with 2 param")
    }

    companion object {
        private const val MY_STYLE: Int = 1
    }

}

