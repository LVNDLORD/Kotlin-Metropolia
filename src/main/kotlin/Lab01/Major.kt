package org.example.Lab01

class Major(name: String) {
    private var students = arrayListOf<Student>()

    fun addStudent(student: Student) = students.add(student)

    fun stats(): Triple<Double, Double, Double> {
        val allAverages = students.map { it.weightedAverage() }
        val minAvg = allAverages.minOrNull() ?: 0.0
        val maxAvg = allAverages.maxOrNull() ?: 0.0
        val avgAvg = if (allAverages.isNotEmpty()) allAverages.average() else 0.0

        return Triple(minAvg, maxAvg, avgAvg)
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {

        val filteredCourses = students.map { student ->
            student.courses.filter { it.name == courseName }
        }.filter { it.isNotEmpty() }

        val averages = filteredCourses.map { courses ->
            val totalCredits = courses.sumOf { it.credits }
            val weightedSum = courses.sumOf { it.grade * it.credits }
            weightedSum / totalCredits
        }

        val minAvg = averages.minOrNull() ?: 0.0
        val maxAvg = averages.maxOrNull() ?: 0.0
        val avgAvg = if (averages.isNotEmpty()) averages.average() else 0.0

        return Triple(minAvg, maxAvg, avgAvg)
    }

}
