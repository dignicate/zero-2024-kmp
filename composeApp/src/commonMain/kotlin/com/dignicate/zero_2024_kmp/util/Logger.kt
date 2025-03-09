package com.dignicate.zero_2024_kmp.util

import org.lighthousegames.logging.logging

var logger: UntaggedLogWrapper = LightHouseGamesLogger

fun setTestLogger() {
    logger = TestLogger
}

private object LightHouseGamesLogger : UntaggedLogWrapper {
    private val wrappers: MutableMap<String, TaggedLogWrapper> = mutableMapOf()

    override fun tag(tag: String): LogWrapper {
        return wrapperWithTag(tag)
    }

    private fun wrapperWithTag(tag: String): TaggedLogWrapper {
        return wrappers.getOrPut(tag) { TaggedLogWrapper(tag, this) }
    }

    override fun d(message: String) {
        logging().debug { message }
    }

    override fun i(message: String) {
        logging().info { message }
    }

    override fun w(throwable: Throwable?, message: String) {
        logging().warn(throwable) { message }
    }

    override fun e(throwable: Throwable?, message: String) {
        logging().error(throwable) { message }
    }

    override fun v(message: String) {
        logging().verbose { message }
    }
}

interface LogWrapper {
    fun d(message: String)
    fun i(message: String)
    fun w(throwable: Throwable?, message: String)
    fun e(throwable: Throwable?, message: String)
    fun v(message: String)

    interface Untagged {
        fun tag(tag: String): LogWrapper
    }
}

interface UntaggedLogWrapper : LogWrapper, LogWrapper.Untagged

data class TaggedLogWrapper(
    val tag: String,
    val wrapper : LogWrapper,
) : LogWrapper {
    override fun d(message: String) {
        wrapper.d(message)
    }

    override fun i(message: String) {
        wrapper.i(message)
    }

    override fun w(throwable: Throwable?, message: String) {
        wrapper.w(throwable, message)
    }

    override fun e(throwable: Throwable?, message: String) {
        wrapper.e(throwable, message)
    }

    override fun v(message: String) {
        wrapper.v(message)
    }
}

private object TestLogger : UntaggedLogWrapper {
    override fun d(message: String) {
        println("DEBUG: $message")
    }

    override fun i(message: String) {
        println("INFO: $message")
    }

    override fun w(throwable: Throwable?, message: String) {
        println("WARN: $message")
        throwable?.printStackTrace()
    }

    override fun e(throwable: Throwable?, message: String) {
        println("ERROR: $message")
        throwable?.printStackTrace()
    }

    override fun v(message: String) {
        println("VERBOSE: $message")
    }

    override fun tag(tag: String): LogWrapper {
        return TaggedLogWrapper(tag, this)
    }
}
