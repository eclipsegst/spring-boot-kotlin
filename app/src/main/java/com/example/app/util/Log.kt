package com.example.app.util

import org.apache.logging.log4j.LogManager

object Log {
    private val LOG = LogManager.getLogger(
        Log::class.java
    )

    fun d(message: String?) {
        LOG.debug(message)
    }

    fun d(tag: String, message: String) {
        LOG.debug("[$tag] $message")
    }

    fun i(message: String?) {
        LOG.info(message)
    }

    fun i(tag: String, message: String) {
        LOG.info("[$tag] $message")
    }

    fun w(message: String?) {
        LOG.warn(message)
    }

    fun w(tag: String, message: String) {
        LOG.warn("[$tag] $message")
    }

    fun e(message: String?) {
        LOG.error(message)
    }

    fun e(tag: String, message: String) {
        LOG.error("[$tag] $message")
    }

    fun e(message: String?, t: Throwable?) {
        LOG.error(message, t)
    }

    fun f(message: String?) {
        LOG.fatal(message)
    }

    fun f(tag: String, message: String) {
        LOG.fatal("[$tag] $message")
    }
}
