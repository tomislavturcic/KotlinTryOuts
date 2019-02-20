package com.tt.kotlintryout.utils

import android.util.Log

class DebugLogger : Logger {

    private companion object {

        const val TAG = "Tomislav"
    }

    override fun logD(tag: String, value: String) {
        Log.d(tag, value)
    }

    override fun logD(value: String) {
        logD(TAG, value)
    }

    override fun logE(tag: String, value: String) {
        Log.e(tag, value)
    }

    override fun logE(value: String) {
        logE(TAG, value)
    }
}