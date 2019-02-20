package com.tt.kotlintryout.utils

class LogUtils {

    companion object {

        private var logger = Logger.EMPTY

        @JvmStatic
        fun logD(tag : String, value : String){
            logger.logD(tag, value)
        }

        @JvmStatic
        fun logD(value : String){
            logger.logD(value)
        }

        @JvmStatic
        fun logE(tag : String, value : String){
            logger.logE(tag, value)
        }

        @JvmStatic
        fun logE(value : String){
            logger.logE(value)
        }

        @JvmStatic
        fun setLogger(logger: Logger){
            this.logger = logger
        }

    }

}