package com.dignicate.zero_2024_kmp.util

import org.lighthousegames.logging.logging

val logger: LogWrapper = Logger

private object Logger : LogWrapper {
    private val wrappers: MutableMap<String, TaggedLogWrapper> = mutableMapOf()

    fun tag(tag: String): TaggedLogWrapper {
        return wrapperWithTag(tag)
    }

    private fun wrapperWithTag(tag: String): TaggedLogWrapper {
        return wrappers.getOrPut(tag) { TaggedLogWrapper(tag) }
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
}

data class TaggedLogWrapper(val tag: String) : LogWrapper {
    override fun d(message: String) {
        logging().debug(tag) { message }
    }

    override fun i(message: String) {
        logging().info(tag) { message }
    }

    override fun w(throwable: Throwable?, message: String) {
        logging().warn(throwable, tag) { message }
    }

    override fun e(throwable: Throwable?, message: String) {
        logging().error(throwable, tag) { message }
    }

    override fun v(message: String) {
        logging().verbose(tag) { message }
    }
}
