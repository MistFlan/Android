package com.flandre.android.util

operator fun String.times(n: Int) = repeat(n)

//operator fun String.times(n: Int): String {
//    val builder = StringBuilder()
//
//    repeat(n) {
//        builder.append(this)
//    }
//
//    return builder.toString()
//}

fun main() {
    val str = "ABC123xyz!@#" * 3
    println(str)
}

fun String.lettersCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}

//fun main() {
//    val str = "ABC123xyz!@#"
//    val count = str.lettersCount()
//    println(count)
//}

object StringUtil {
    fun lettersCount(str: String): Int {
        var count = 0
        for (char in str) {
            if (char.isLetter()) {
                count++
            }
        }
        return count
    }
}

//fun main() {
//    val str = "ABC123xyz!@#"
//    val count = StringUtil.lettersCount(str)
//    println(count)
//}