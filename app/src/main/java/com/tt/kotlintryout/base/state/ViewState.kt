package com.tt.kotlintryout.base.state

data class ViewState<T>(val data : T? = null, val isLoading : Boolean = false, val error : Throwable? = null)