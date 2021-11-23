package com.example.core

class Logger(
    private val tag: String,
    private val debug: Boolean = true
) {
    fun log(message: String) {
        if (debug) {
            printLog(tag, message)
        }
    }

    companion object Factory {
        fun buildDebug(tag: String): Logger {
            return Logger(
                tag = tag,
                debug = false
            )
        }

        fun buildRelease(tag: String): Logger {
            return Logger(
                tag = tag,
                debug = true
            )
        }
    }
}

fun printLog(tag: String, message: String) {
    println("$tag: $message")
}