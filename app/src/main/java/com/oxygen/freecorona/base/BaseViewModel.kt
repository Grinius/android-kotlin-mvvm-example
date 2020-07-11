package com.oxygen.freecorona.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oxygen.freecorona.arcextensions.ObservableViewModel
import com.oxygen.freecorona.arcextensions.ViewLiveData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel<T : ViewLiveData> : ObservableViewModel() {

    abstract fun getTag(): String
    abstract fun getInitialViewLiveDataValue(): T

    private val _viewLiveData = MutableLiveData<T>().apply {
        value = getInitialViewLiveDataValue()
    }
    val viewLiveData: LiveData<T> = _viewLiveData

    protected val compositeDisposable = CompositeDisposable()

    protected fun getViewLiveDataValue() =  _viewLiveData.value ?: getInitialViewLiveDataValue()

    protected fun notifyLiveDataObservers() = _viewLiveData.postValue(_viewLiveData.value)

    protected fun <T : Any> Observable<T>.defaultSubscribeBy(
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {},
        onNext: (T) -> Unit = {},
        observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()
    ) = this.subscribeOn(Schedulers.io())
        .observeOn(observeOnScheduler)
        .subscribeBy(onError, onComplete, onNext)
        .apply {
            compositeDisposable.add(this)
        }

    protected fun <T : Any> Single<T>.defaultSubscribeBy(
        onError: (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    ): Disposable {
        val disposable = this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onError, onSuccess)
        compositeDisposable.add(disposable)
        return disposable
    }

    protected fun Completable.defaultSubscribeBy(
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {}
    ): Disposable {
        val disposable = this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onError, onComplete)
        compositeDisposable.add(disposable)
        return disposable
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}