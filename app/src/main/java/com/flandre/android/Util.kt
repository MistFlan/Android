package com.flandre.android

class Util {
    fun doAction1() {
        println("do action1")
    }

    companion object {

        @JvmStatic
        fun doAction2() {
            println("do action2")
        }
    }
}

//class Util {
//    fun doAction1() {
//        println("do action1")
//    }
//
//    companion object {
//        fun doAction2() {
//            println("do action2")
//        }
//    }
//}

//object Util {
//    fun doAction() {
//        println("do action")
//    }
//}