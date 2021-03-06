package com.paul.wang.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.paul.wang.model.repository.MainRepository
import com.paul.wang.model.response.GithubRepoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    // An easy way to init LiveData and
    val someLiveData: LiveData<String> = liveData {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                // Do something here in the background thread.
            }
        }

        // Load some data using suspend function.
        emit("abcd")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    private fun getRepoData(repoNameList: MutableLiveData<List<RepoItemData>>) {
        mainRepository.getRepoData()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { githubRepoResponse -> mapGitResponseToView(githubRepoResponse) }
            .subscribeWith(getRepoDataObserver(repoNameList))
            .run { disposable.add(this) }
    }

    private fun mapGitResponseToView(githubRepoResponse: GithubRepoResponse): List<RepoItemData> {
        Timber.tag("on_thread").d("getRepoData.map{} ${Thread.currentThread()}")
        val repoItemDataList = mutableListOf<RepoItemData>()
        githubRepoResponse.forEach { githubItem ->
            val repoItem = RepoItemData(githubItem.name, githubItem.description, URL(githubItem.html_url))
            repoItemDataList.add(repoItem)
        }
        return repoItemDataList
    }

    private fun getRepoDataObserver(repoNameList: MutableLiveData<List<RepoItemData>>): DisposableObserver<List<RepoItemData>> {
        return object : DisposableObserver<List<RepoItemData>>() {
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
        }
    }
}
