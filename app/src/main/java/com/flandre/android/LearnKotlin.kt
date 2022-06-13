package com.flandre.android

import kotlin.math.max

// 3.4 面向对象
fun main() {
    val student1 = Student()
    student1.eat()
    val student2 = Student("HaJiang", 7)
    student2.eat()
    val student3 = Student("S123", 5, "HaJiang", 7)
    student3.eat()
}

//fun main() {
//    val student = Student("S123", 5, "HaJiang", 7)
//    student.eat()
//}

//fun main() {
//    val student = Student("S123", 5)
//}

//fun main() {
//    val p = Person()
//    p.name = "HaJiang"
//    p.age = 7
//    p.eat()
//}

// 3.3 逻辑控制
//fun main() {
//    for (i in 10 downTo 1) {
//        println(i)
//    }
//}

//fun main() {
//    for (i in 0 until 10 step 2) {
//        println(i)
//    }
//}

//fun main() {
//    for (i in 0..10) {
//        println(i)
//    }
//}

//fun getScore(name: String) = if (name == "HaJiang") {
//    8
//} else if (name == "ayu") {
//    110
//} else 0

//fun getScore(name: String) = when (name) {
//    "HaJiang" -> 8
//    "Yu" -> 110
//    else -> 0
//}

//fun getScore(name: String) = when {
//    name == "HaJiang" -> 8
//    name == "Yu" -> 110
//    else -> 0
//}

fun getScore(name: String) = when {
    name.startsWith("H") -> 8
    name == "Yu" -> 110
    else -> 0
}

fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number is Unknown")
    }
}

//fun largerNumber(num1: Int, num2: Int): Int {
//    var value = 0
//    if (num1 > num2) {
//        value = num1
//    } else {
//        value = num2
//    }
//    return value
//}

//fun largerNumber(num1: Int, num2: Int): Int {
//    val value = if (num1 > num2) {
//        num1
//    } else {
//        num2
//    }
//    return value
//}

//fun largerNumber(num1: Int, num2: Int): Int {
//    return if (num1 > num2) {
//        num1
//    } else {
//        num2
//    }
//}

//fun largerNumber(num1: Int, num2: Int) = if (num1 > num2) {
//    num1
//} else {
//    num2
//}

fun largerNumber(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

// 3.2 函数
//fun main() {
//    val hajiang = 100
//    val ayu = 110
//    val largerNum = largerNumber(hajiang, ayu)
//    println("largerNumber is " + largerNum)
//
//    println(getScore("HaJiang"))
//
//    checkNumber(123)
//}

//fun largerNumber(num1: Int, num2: Int): Int {
//    return max(num1, num2)
//}

//fun largerNumber(num1: Int, num2: Int): Int = max(num1, num2)

//fun largerNumber(num1: Int, num2: Int) = max(num1, num2)

// 3.1 变量
//fun main() {
//    var a: Int = 10
//    a = a * 10
//    println("a = " + a)
//}

fun methodName(param1: Int, param2: String): Int {
    return 0
}