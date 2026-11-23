# Student Grade Report (Kotlin)

A small command-line Kotlin program that reads student marks, checks them for mistakes, and prints a report card with grades and class statistics. Built while learning Kotlin from scratch as part of my Android development journey.

## What it does

- Stores student names and marks in a `Map`
- **Validates** the data: marks that are missing (`null`), negative, or above 100 are reported and left out
- Gives each valid student a grade:

  | Marks | Grade |
  |---|---|
  | 90 and above | A |
  | 75 to 89 | B |
  | 50 to 74 | C |
  | below 50 | Fail |

- Prints a report card, grade counts, class average, number of students who passed and failed, and the top student

### Sample output

```
============ Invalid Entries ============
Priya: invalid mark -88 (marks should be between 0 and 100)
Kiran: invalid mark 105 (marks should be between 0 and 100)
Sam: no mark entered
============ Report Card ============
Aasha: 92 -> A
Ravi: 78 -> B
Meena: 45 -> Fail
John: 65 -> C
============ Grade Count ============
A : 1
B : 1
C : 1
Fail : 1
Class average : 70.0
Passed students : 3 of 4
Failed students : 1 of 4
Top student : Aasha (92)
```

## What I learned

### `val` and `var`
- `val` is read-only (cannot be reassigned). I used it for things that do not change, like the students map.
- `var` can be changed. I used it for counters and running totals such as `total`, `passed` and `failed`.

```kotlin
val students = mapOf("Aasha" to 92)
var passed = 0
passed++
```

### `if` and `when`
- `if / else if / else` for decisions, such as checking invalid marks.
- `when` as a cleaner alternative to many `if` statements. It can return a value.

```kotlin
fun getGrade(score: Int): String = when {
    score >= 90 -> "A"
    score >= 75 -> "B"
    score >= 50 -> "C"
    else -> "Fail"
}
```

- Ranges such as `mark in 0..100` check whether a number is inside a range.

### Loops
- `for` loops over a map's values, or over keys and values together using destructuring.
- `continue` skips the current item and moves to the next one.

```kotlin
for ((name, marks) in students) {
    if (marks == null) {
        println("$name: no mark entered")
        continue
    }
}
```

### Functions
- Splitting the program into small functions that each do one job:
  `getGrade`, `isValidMark`, `calculateAverage`, `countPassed`, `countFailed`, `findTopStudent`, `countGrades`
- Parameters and return types, single-expression functions (`= when { ... }`), and returning two values with `Pair` (destructured as `val (topName, topMarks) = ...`).
- Naming style: functions and variables use camelCase, and function names start with a verb.

### Null safety
- A normal type (`Int`) can never be `null`. A nullable type (`Int?`) can.
- After a null check, Kotlin treats the value as non-null (smart cast), so no `!!` is needed.
- `?.` (safe call) and `?:` (elvis operator) are the other tools I met for handling `null`.
- A map with a `null` value becomes `Map<String, Int?>`, so I validated first and kept the good data in a separate `Map<String, Int>`.

### Other things picked up
- `Map`, `mutableMapOf` and `mapOf`
- String templates: `"$name"` and `"${getGrade(marks)}"`
- Checking for an empty map before dividing, to avoid dividing by zero

## How to run

### What you need
- A JDK (Java 17 or newer). Check with `java -version`.
- The Kotlin compiler. Check with `kotlinc -version`.
  (Download from the [Kotlin GitHub releases](https://github.com/JetBrains/kotlin/releases), extract it, and add its `bin` folder to your `PATH`.)

### From the terminal

Save the code as `StudentGrades.kt`, then in that folder run:

```
kotlinc StudentGrades.kt -include-runtime -d StudentGrades.jar
java -jar StudentGrades.jar
```

The first compile can take a few seconds.

### From an IDE

Open the file in Android Studio or IntelliJ IDEA and click the green Run arrow next to `fun main()`.

## Ideas for next steps

- Replace the `Map` with a `data class Student(val name: String, val marks: Int?)` and a `List<Student>`
- Read marks typed in by the user with `readln()`
- Use built-in helpers such as `filter`, `sum`, `count` and `maxByOrNull`
- Move the logic into a simple Android app with Jetpack Compose
