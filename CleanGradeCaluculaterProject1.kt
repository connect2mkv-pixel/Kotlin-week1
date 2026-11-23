fun getGrade(score: Int): String = when {
    score >= 90 -> "A"
    score >= 75 -> "B"
    score >= 50 -> "C"
    else -> "Fail"
}

fun isValidMark(mark: Int): Boolean = mark in 0..100

fun calculateAverage(students: Map<String, Int>): Double {
    if (students.isEmpty()) return 0.0
    var total = 0
    for (marks in students.values) {
        total += marks
    }
    return total.toDouble() / students.size
}

fun countPassed(students: Map<String, Int>): Int {
    var passed = 0
    for (marks in students.values) {
        if (getGrade(marks) != "Fail") passed++
    }
    return passed
}

fun countFailed(students: Map<String, Int>): Int {
    var failed = 0
    for (marks in students.values) {
        if (getGrade(marks) == "Fail") failed++
    }
    return failed
}

fun findTopStudent(students: Map<String, Int>): Pair<String, Int> {
    var topName = ""
    var topMarks = -1
    for ((name, marks) in students) {
        if (marks > topMarks) {
            topMarks = marks
            topName = name
        }
    }
    return Pair(topName, topMarks)
}

fun countGrades(students: Map<String, Int>): Map<String, Int> {
    var countA = 0
    var countB = 0
    var countC = 0
    var countFail = 0
    for (marks in students.values) {
        when (getGrade(marks)) {
            "A" -> countA++
            "B" -> countB++
            "C" -> countC++
            else -> countFail++
        }
    }
    return mapOf("A" to countA, "B" to countB, "C" to countC, "Fail" to countFail)
}

fun main() {
    val students = mapOf(
        "Aasha" to 92,
        "Ravi" to 78,
        "Meena" to 45,
        "Priya" to -88,   // negative: impossible
        "John" to 65,
        "Kiran" to 105,   // over 100: impossible
        "Sam" to null     // missing: impossible
    )

    val validStudents = mutableMapOf<String, Int>()

    println("============ Invalid Entries ============")
    for ((name, marks) in students) {
        if (marks == null) {
            println("$name: no mark entered")
            continue
        }
        if (!isValidMark(marks)) {
            println("$name: invalid mark $marks (marks should be between 0 and 100)")
            continue
        }
        validStudents[name] = marks
    }

    println("============ Report Card ============")
    for ((name, marks) in validStudents) {
        println("$name: $marks -> ${getGrade(marks)}")
    }

    println("============ Grade Count ============")
    for ((grade, count) in countGrades(validStudents)) {
        println("$grade : $count")
    }

    val average = calculateAverage(validStudents)
    println("Class average : $average")

    val passed = countPassed(validStudents)
    val failed = countFailed(validStudents)
    println("Passed students : $passed of ${validStudents.size}")
    println("Failed students : $failed of ${validStudents.size}")

    if (validStudents.isNotEmpty()) {
        val (topName, topMarks) = findTopStudent(validStudents)
        println("Top student : $topName ($topMarks)")
    } else {
        println("Top student : none (no valid marks)")
    }
}

