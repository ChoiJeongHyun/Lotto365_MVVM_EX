package kr.co.example.lotto365_mvp.Utils

import android.util.Log

class PLog(message: Any?) {
    companion object {
        private var TAG = "[PSE framework]"
        fun setTAG(TAG: String) {
            Companion.TAG = "[PSE framework] / $TAG"
        }

        fun v(message: Any?) {
            if (message == null) Log.v(TAG, "Null Message") else {
                val stringMessage = message.toString()
                Log.v(TAG, stringMessage)
            }
        }

        fun e(message: Any?) {
            if (message == null) Log.v(TAG, "Null Message") else {
                val stringMessage = message.toString()
                Log.e(TAG, stringMessage)
            }
        }

        fun e(vararg messages: Any?) {
            if (messages == null) Log.v(TAG, "Null Message") else {
                for (message in messages) {
                    var stringMessage = "Null Message"
                    if (message != null) stringMessage = message.toString()
                    Log.e(TAG, stringMessage)
                }
            }
        }

        fun e(T: Class<*>, message: Any?) {
            if (message == null) Log.v(TAG, "Null Message") else {
                val stringMessage = T.simpleName + " : " + message
                Log.e(TAG, stringMessage)
            }
        }

        fun w(message: Any?) {
            if (message == null) Log.v(TAG, "Null Message") else {
                val stringMessage = message.toString()
                Log.w(TAG, stringMessage)
            }
        }
    }

    init {
        if (message == null) Log.e(TAG, "Null Message") else {
            val stringMessage = message.toString()
            Log.e(TAG, stringMessage)
        }
    }
}