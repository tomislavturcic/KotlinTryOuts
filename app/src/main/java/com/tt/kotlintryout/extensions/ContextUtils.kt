package com.tt.kotlintryout.extensions

import android.content.Context
import android.widget.Toast

fun Context.showToast(textId : Int, duration : Int = Toast.LENGTH_SHORT) {
    showToast(getString(textId), duration)
}

fun Context.showToast(textId : String, duration : Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, textId, duration).show()
}