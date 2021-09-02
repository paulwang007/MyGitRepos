package com.paul.wang.androidstarter.model.repository

import com.paul.wang.androidstarter.model.network.GithubService
import com.paul.wang.androidstarter.model.response.GithubRepoResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofit: Retrofit) {

    fun getRepoData(): Observable<GithubRepoResponse> {
        Timber.tag("on_thread").d("${Thread.currentThread()}")
        return retrofit.create(GithubService::class.java).getRepo("paulwang007")
    }
}
