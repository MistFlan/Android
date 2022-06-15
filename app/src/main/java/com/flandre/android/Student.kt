package com.flandre.android

class Student(val sno: String = "", val grade: Int? = null, name: String = "", age: Int = 8) :
    Person(name, age), Study {
    override fun readBooks() {
        println(name + " is reading.")
    }
}

//class Student(name: String, age: Int) : Person(name, age), Study {
//    override fun readBooks() {
//        println(name + " is reading.")
//    }
//}

//class Student(name: String, age: Int) : Person(name, age), Study {
//    override fun readBooks() {
//        println(name + " is reading.")
//    }
//
//    override fun doHomework() {
//        println(name + " is doing homework.")
//    }
//}

//class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) {
//    init {
//        println("sno is : " + sno)
//        println("grade is : " + grade)
//    }
//
//    constructor(name: String, age: Int) : this("", 0, name, age) {}
//
//    constructor() : this("", 0) {}
//}

class Student1 : Person {
    constructor(name: String, age: Int) : super(name, age) {}
}