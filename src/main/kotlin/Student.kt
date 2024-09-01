package org.example

class Student(name: String, age: Int) : Human(name, age) {
    var courses = arrayListOf<CourseRecord>()

    fun addCourse(courseRecord: CourseRecord) = courses.add(courseRecord)

    fun getCourses(): List<CourseRecord> = courses

    fun weightedAverage(year: Int? = null): Double {
        val coursesToCalculate = if (year == null) {
            courses
        } else {
            courses.filter { it.yearCompleted == year }
        }

        val totalWeightedGrade: Double = coursesToCalculate.sumOf { it.credits * it.grade }
        val totalCredits = coursesToCalculate.sumOf { it.credits }
        return if (totalCredits > 0) totalWeightedGrade / totalCredits else 0.0
    }

    fun minMaxGrades(): Pair<Double, Double> = Pair(courses.minOf { it.grade }, courses.maxOf { it.grade })

    override fun toString(): String {
        return "Student name=$name, age=$age, (courses=$courses)"
    }


}
