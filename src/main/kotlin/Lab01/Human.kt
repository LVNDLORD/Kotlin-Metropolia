package org.example.Lab01

open class Human(open var name: String, open var age: Int) {

    fun getOlder(): Int = age++
}
