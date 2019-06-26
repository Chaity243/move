package com.chaity.easymove.domain

//interface to remove domain layer dependency on Timber library
interface Logger {
    fun d(msg: String, vararg values: Any?)
}