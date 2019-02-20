package com.tt.kotlintryout.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val subscriptions = CompositeDisposable()

    protected fun addSubscription(disposable: Disposable){
        subscriptions.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

}