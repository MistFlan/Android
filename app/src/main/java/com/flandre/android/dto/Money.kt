package com.flandre.android.dto

class Money(val value: Int) {
    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }

    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }
}

fun main() {
    val money1 = Money(5)
    val money2 = Money(10)
    val money3 = money1 + money2
    val money4 = money3 + 20
    println(money4.value)
}

//fun main() {
//    val money1 = Money(5)
//    val money2 = Money(10)
//    val money3 = money1 + money2
//    println(money3.value)
//}

fun getRandomLengthString(str: String): String {
    val n = (1..20).random()
    val builder = StringBuilder()
    repeat(n) {
        builder.append(str)
    }
    return builder.toString()
}