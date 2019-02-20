package com.tt.kotlintryout.base.state

fun <T> ViewState<T>.isError() : Boolean {
    return error != null
}

fun <T> ViewState<T>.isSuccess() : Boolean {
    return data != null
}

fun <T> ViewState<List<T>>.isEmpty() : Boolean {
    return data != null && data.isEmpty()
}

fun <T> ViewState<List<T>>.isEmptyErrorOrLoading() : Boolean {
    return isError() || isLoading || (data != null && data.isEmpty())
}
