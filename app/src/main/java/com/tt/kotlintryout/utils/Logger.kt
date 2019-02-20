package com.tt.kotlintryout.utils

interface Logger {

    fun logD(tag : String, value : String)

    fun logD(value: String)

    fun logE(tag : String, value : String)

    fun logE(value: String)

    companion object {

        val EMPTY = object : Logger {
            override fun logD(tag: String, value: String) {

            }

            override fun logD(value: String) {

            }

            override fun logE(tag: String, value: String) {

            }

            override fun logE(value: String) {

            }

        }

    }

}