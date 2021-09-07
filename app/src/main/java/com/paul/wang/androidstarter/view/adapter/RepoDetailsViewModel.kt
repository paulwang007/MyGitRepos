package com.paul.wang.androidstarter.view.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paul.wang.androidstarter.model.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(private val detailsRepo: DetailsRepository) : ViewModel() {
    private val _repoDetailsLiveData: MutableLiveData<RepoDetailsData> by lazy { MutableLiveData<RepoDetailsData>() }
    val repoDetailsLiveData: LiveData<RepoDetailsData> by lazy { _repoDetailsLiveData }
    fun getRepoDetails(userName: String, repoName: String) {
        detailsRepo.getRepoDetails(userName, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                RepoDetailsData(
                    it.private,
                    it.owner.login,
                    it.owner.avatar_url,
                    it.created_at,
                    it.language,
                    it.license.name,
                    it.open_issues,
                    it.default_branch
                )
            }
            .subscribeWith(object : DisposableObserver<RepoDetailsData>() {
                override fun onNext(t: RepoDetailsData) {
                    _repoDetailsLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

                override fun onComplete() {
                    Timber.d("getRepoDetails.onComplete()")
                }
            })
    }
}

data class RepoDetailsData(
    val isPrivate: Boolean,
    val owner: String,
    val ownerAvatar: String,
    val created: String,
    val language: String,
    val license: String,
    val openIssues: Int,
    val defaultBranch: String
)
