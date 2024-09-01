package org.example

open class CourseRecord(var name: String, var yearCompleted: Int, var credits: Int, var grade: Double) {
    override fun toString(): String {
        return "CourseRecord(name='$name', yearCompleted=$yearCompleted, credits=$credits, grade=$grade)"
    }
}
