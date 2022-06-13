package com.flandre.android

class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) {
    init {
        println("sno is : " + sno)
        println("grade is : " + grade)
    }

    constructor(name: String, age: Int) : this("", 0, name, age) {}

    constructor() : this("", 0) {}
}

class Student1 : Person {
    constructor(name: String, age: Int) : super(name, age) {}
}