package com.tt.kotlintryout.extensions

import com.tt.kotlintryout.base.state.ViewState
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.subOnIoObserveOnMain(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.subOnIoObserveOnMain(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.subOnIoObserveOnMain(): Completable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.toViewState(): Observable<ViewState<T>> {
    return toObservable()
        .toViewState()
}

fun <T> Observable<T>.toViewState(): Observable<ViewState<T>> {
    return map { t -> ViewState(t) }
        .onErrorReturn { throwable -> ViewState(error = throwable) }
        .startWith(ViewState(isLoading = true))
        .subOnIoObserveOnMain()
}
