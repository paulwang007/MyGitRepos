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
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    val repoListLiveData: LiveData<List<RepoItemData>> by lazy {
        val repoNameList = MutableLiveData<List<RepoItemData>>()
        getRepoData(repoNameList)
        repoNameList
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    private fun getRepoData(repoNameList: MutableLiveData<List<RepoItemData>>) {
        mainRepository.getRepoData()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { githubRepoResponse ->
                Timber.tag("on_thread").d("getRepoData.map{} ${Thread.currentThread()}")
                val repoItemDataList = mutableListOf<RepoItemData>()
                githubRepoResponse.forEach { githubItem ->
                    val repoItem = RepoItemData(githubItem.name, githubItem.description, URL(githubItem.html_url))
                    repoItemDataList.add(repoItem)
                }
                repoItemDataList
            }
            .subscribeWith(object : DisposableObserver<List<RepoItemData>>() {
                override fun onNext(t: List<RepoItemData>) {
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
