package com.dignicate.zero_2024_kmp.util

import org.lighthousegames.logging.logging

var logger: UntaggedLogWrapper = LightHouseGamesLogger

fun setTestLogger() {
    logger = TestLogger
}

private object LightHouseGamesLogger : UntaggedLogWrapper {
    private val wrappers: MutableMap<String, LightHouseGamesLoggerTagged> = mutableMapOf()

    private const val DEFAULT_TAG = "no_tag"

    override fun tag(tag: String): LogWrapper {
        return wrapperWithTag(tag)
    }

    private fun wrapperWithTag(tag: String): LightHouseGamesLoggerTagged {
        return wrappers.getOrPut(tag) { LightHouseGamesLoggerTagged(tag) }
    }

    override fun d(message: String) {
        logging(DEFAULT_TAG).debug { message }
    }

    override fun i(message: String) {
        logging(DEFAULT_TAG).info { message }
    }

    override fun w(throwable: Throwable?, message: String) {
        logging(DEFAULT_TAG).warn(throwable) { message }
    }

    override fun e(throwable: Throwable?, message: String) {
        logging(DEFAULT_TAG).error(throwable) { message }
    }

    override fun v(message: String) {
        logging(DEFAULT_TAG).verbose { message }
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

data class LightHouseGamesLoggerTagged(
    val tag: String,
) : LogWrapper {
    override fun d(message: String) {
        logging(tag).d { message }
    }

    override fun i(message: String) {
        logging(tag).i { message }
    }

    override fun w(throwable: Throwable?, message: String) {
        logging(tag).w(throwable) { message }
    }

    override fun e(throwable: Throwable?, message: String) {
        logging(tag).e(throwable) { message }
    }

    override fun v(message: String) {
        logging(tag).v { message }
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
        return this
    }
}
