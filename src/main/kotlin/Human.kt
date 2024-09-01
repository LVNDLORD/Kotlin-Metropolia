package org.example

open class Human(open var name: String, open var age: Int) {

    fun getOlder(): Int = age++
}
