package org.example.Lab01

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val major = Major("True Programming")
    val students = ArrayList<Student>()
    fun createStudents() {
        var student: Student
        student = Student("Enzio Benzino", 21)
        student.addCourse(CourseRecord("Kotlin basics", 2023,5, 5.0))
        student.addCourse(CourseRecord("Java basics", 2023, 5, 1.0))
        student.addCourse(CourseRecord("Scala basics", 2023, 3, 2.0))
        students.add(student)
        student = Student("Abebe Bikila", 23)
        student.addCourse(CourseRecord("Kotlin basics", 2023, 5, 2.0))
        students.add(student)


        for (student in students) {
            major.addStudent(student)
        }
    }

    createStudents()
    println(students.toString())
    val (minAvg, maxAvg, avgAvg) = major.stats("Kotlin basics")
    println("Min Average: $minAvg, Max Average: $maxAvg, Average of Averages: $avgAvg")
}

