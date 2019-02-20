package com.tt.kotlintryout.base.state

import androidx.lifecycle.MutableLiveData

class StateLiveData<T> : MutableLiveData<ViewState<T>>() {

    fun setLoading(){
        value = ViewState()
    }

    fun postLoading(){
        postValue(ViewState())
    }

    fun setData(data : T){
        value = ViewState(data)
    }

    fun postData(data : T){
        postValue(ViewState(data))
    }

    fun setError(throwable : Throwable){
        value = ViewState(error = throwable)
    }

    fun postError(throwable : Throwable){
        postValue(ViewState(error = throwable))
    }
}