package com.flandre.android

import java.lang.StringBuilder
import kotlin.math.max




// Kotlin课堂 1、标准函数
//fun main() {
//    val list = listOf("HaJiang", "ayu", "Flan")
//    val result = StringBuilder().apply {
//        append("Start roll call. \n")
//        for (name in list) {
//            append(name).append(" \n")
//        }
//        append("EveryBody gather.")
//    }
//
//    println(result.toString())
//}

//fun main() {
//    val list = listOf("HaJiang", "ayu", "Flan")
//    val result = StringBuilder().run {
//        append("Start roll call. \n")
//        for (name in list) {
//            append(name).append(" \n")
//        }
//        append("EveryBody gather.")
//        toString()
//    }
//
//    println(result)
//}

//fun main() {
//    val list = listOf("HaJiang", "ayu", "Flan")
//    val result = with(StringBuilder()) {
//        append("Start roll call. \n")
//        for (name in list) {
//            append(name).append(" \n")
//        }
//        append("EveryBody gather.")
//        toString()
//    }
//
//    println(result)
//}

//fun main() {
//    val list = listOf("HaJiang", "ayu", "Flan")
//    val builder = StringBuilder()
//    builder.append("Start roll call. \n")
//    for (name in list) {
//        builder.append(name).append(" \n")
//    }
//    builder.append("EveryBody gather.")
//    val result = builder.toString()
//    println(result)
//}

// 3.7 其他
//fun main() {
//    val student = Student(sno = "8", grade = 1, name = "HaJiang")
//    student.readBooks()
//    student.doHomework()
//    student.eat()
//}

//fun main() {
//    printParams(str = "world", num = 123)
//}
//
//fun printParams(num: Int, str: String = "hello") {
//    println("num is $num, str is $str")
//}

//fun main() {
//    printParams(123)
//}
//
//fun printParams(num: Int, str: String = "hello") {
//    println("num is $num, str is $str")
//}

//fun main() {
//    val person = Person("HaJiang", 8)
//    var str = "hello, ${person.name}. nice to meet you."
//    println(str)
//}

//val map = mapOf("name" to "HaJiang")
//
//fun main() {
//    var str = "hello, ${map["name"]}. nice to meet you."
//    println(str)
//}

// 3.6 空指针异常
//var study: Study? = null
//
//fun doStudy() {
//    study?.let {
//        it.readBooks()
//        it.doHomework()
//    }
//}

//fun doStudy(study: Study?) {
//    study?.let {
//        it.readBooks()
//        it.doHomework()
//    }
//}

//fun doStudy(study: Study?) {
//    study?.let { study ->
//        study.readBooks()
//        study.doHomework()
//    }
//}

//val content: String? = "hello"
//
//fun main() {
//    if (null != content) {
//        printUpperCase()
//    }
//}
//
//fun printUpperCase() {
//    val upperCase = content!!.toUpperCase()
//    println(upperCase)
//}

//fun getTextLength(text: String?) = text?.length ?: 0

//fun main() {
//    doStudy(null)
//}
//
//fun doStudy(study: Study?) {
//        study?.readBooks()
//        study?.doHomework()
//}

//fun doStudy(study: Study?) {
//    if (null != study) {
//        study.readBooks()
//        study.doHomework()
//    }
//}

// 3.5 Lambda
//fun main() {
//    Thread { println("Thread is running") }.start()
//}

//fun main() {
//    Thread() { println("Thread is running") }.start()
//}

//fun main() {
//    Thread({
//        println("Thread is running")
//    }).start()
//}

//fun main() {
//    Thread(Runnable {
//        println("Thread is running")
//    }).start()
//}

//fun main() {
//    Thread(object : Runnable {
//        override fun run() {
//            println("Thread is running")
//        }
//    }).start()
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val anyResult = list.any { it.length <= 5 }
//    val allResult = list.all { it.length <= 5 }
//    println("anyResult is " + anyResult + ", allResult is " + allResult)
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val newList = list.filter { it.length <= 5 }.map { it.toUpperCase() }
//    for (name in newList) {
//        println(name)
//    }
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val newList = list.map { it.toUpperCase() }
//    for (name in newList) {
//        println(name)
//    }
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val maxLengthName = list.maxByOrNull { it.length }
//    println("max length name is " + maxLengthName)
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val maxLengthName = list.maxByOrNull { name -> name.length }
//    println("max length name is " + maxLengthName)
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val maxLengthName = list.maxByOrNull { name: String -> name.length }
//    println("max length name is " + maxLengthName)
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val maxLengthName = list.maxByOrNull(){ name: String -> name.length }
//    println("max length name is " + maxLengthName)
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val maxLengthName = list.maxByOrNull({ name: String -> name.length })
//    println("max length name is " + maxLengthName)
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val lambda = { name: String -> name.length }
//    val maxLengthName = list.maxByOrNull(lambda)
//    println("max length name is " + maxLengthName)
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    val maxLengthName = list.maxByOrNull { it.length }
//    println("max length name is " + maxLengthName)
//}

//fun main() {
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    var maxLengthName = ""
//    for (name in list) {
//        if (name.length > maxLengthName.length) {
//            maxLengthName = name
//        }
//    }
//    println("max length name is " + maxLengthName)
//}

//fun main() {
//    val map = mapOf("HaJiang" to 8, "ayu" to 1, "Flan" to 233)
//    for ((name, number) in map) {
//        println("name is " + name + " number is " + number)
//    }
//}

//fun main() {
//    val map = mapOf("HaJiang" to 8, "ayu" to 1, "Flan" to 233)
//}

//fun main(){
//    val map = HashMap<String, Int>()
//    map["HaJiang"] = 8
//    map["ayu"] = 1
//    map["Flan"] = 233
//
//    println(map["HaJiang"])
//}

//fun main() {
//    val map = HashMap<String, Int>()
//    map.put("HaJiang", 8)
//    map.put("ayu", 1)
//    map.put("Flan", 233)
//}

//fun main() {
//    val set = mutableSetOf("HaJiang", "ayu", "Flan")
//    set.add("ZhuFanDe")
//    for (name in set) {
//        println(name)
//    }
//}

//fun main() {
//    val set = setOf("HaJiang", "ayu", "Flan")
//    for (name in set) {
//        println(name)
//    }
//}

//fun main(){
//    val list = mutableListOf("HaJiang", "ayu", "Flan")
//    list.add("ZhuFanDe")
//    for (name in list) {
//        println(name)
//    }
//}

//fun main() {
//    val list = listOf("HaJiang", "ayu", "Flan")
//    for (name in list) {
//        println(name)
//    }
//}

// fun main(){
//    val list = ArrayList<String>()
//    list.add("HaJiang")
//    list.add("ayu")
//    list.add("Flan")
//}

// 3.4 面向对象
//fun  main() {
//    Singleton.singletonTest()
//}

//fun main() {
//    val cellPhone1 = CellPhone("Apple", 6666.666)
//    val cellPhone2 = CellPhone("Apple", 6666.666)
//    println(cellPhone1)
//    println("cellphone1 equals cellPhone2 " + (cellPhone1 == cellPhone2))
//}

//fun main() {
//    val student = Student("Hajiang", 7)
//    doStudy(student)
//}
//
//fun doStudy(study: Study) {
//    study.readBooks()
//    study.doHomework()
//}

//fun main() {
//    val student1 = Student()
//    student1.eat()
//    val student2 = Student("HaJiang", 7)
//    student2.eat()
//    val student3 = Student("S123", 5, "HaJiang", 7)
//    student3.eat()
//}

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