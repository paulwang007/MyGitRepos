package com.paul.wang.androidstarter.model.repository

import com.paul.wang.androidstarter.model.data.GithubRepoResponse
import com.paul.wang.androidstarter.model.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofit: Retrofit) {
    fun getRepoData(): GithubRepoResponse? {
        retrofit.create(GithubService::class.java).getRepo("paulwang007").enqueue(object : Callback<GithubRepoResponse> {
            override fun onResponse(call: Call<GithubRepoResponse>, response: Response<GithubRepoResponse>) {
                Timber.d(response.body()?.get(0).toString())
            }

            override fun onFailure(call: Call<GithubRepoResponse>, t: Throwable) {
                Timber.d(t)
            }
        })

        return null
    }
}
