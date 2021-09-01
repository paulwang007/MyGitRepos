package com.paul.wang.androidstarter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paul.wang.androidstarter.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    val repoListLiveData: LiveData<List<String>> by lazy {
        val repoNameList = MutableLiveData<List<String>>()
        getRepoData(repoNameList)
        repoNameList
    }

    fun clearDisposable() {
        disposable.clear()
    }

    private fun getRepoData(repoNameList: MutableLiveData<List<String>>) {
        mainRepository.getRepoData()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { githubRepoResponse ->
                Timber.tag("on_thread").d("getRepoData.map{} ${Thread.currentThread()}")

                val nameList = mutableListOf<String>()
                githubRepoResponse.forEach { githubItem -> nameList.add(githubItem.full_name) }
                nameList
            }
            .subscribeWith(object : DisposableObserver<List<String>>() {
                override fun onNext(t: List<String>) {
                    Timber.tag("on_thread").d("getRepoData.onNext{} ${Thread.currentThread()}")
                    repoNameList.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

                override fun onComplete() {
                    Timber.d("onComplete()")
                }
            })
            .run {
                disposable.add(this)
            }
    }
}
