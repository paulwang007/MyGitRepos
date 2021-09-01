package com.paul.wang.androidstarter.model.repository

import androidx.lifecycle.MutableLiveData
import com.paul.wang.androidstarter.model.data.GithubRepoResponse
import com.paul.wang.androidstarter.model.network.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofit: Retrofit) {

    suspend fun getRepoData(liveData: MutableLiveData<GithubRepoResponse>) {
        Timber.tag("on_thread").d("${Thread.currentThread()}")

        withContext(Dispatchers.IO) {
            Timber.tag("on_thread").d("${Thread.currentThread()}")
            retrofit.create(GithubService::class.java).getRepo("paulwang007").enqueue(object : Callback<GithubRepoResponse> {
                override fun onResponse(call: Call<GithubRepoResponse>, response: Response<GithubRepoResponse>) {
                    liveData.postValue(response.body())
                    Timber.d(response.body()?.get(0).toString())
                    Timber.tag("on_thread").d("${Thread.currentThread()}")
                }

                override fun onFailure(call: Call<GithubRepoResponse>, t: Throwable) {
                    Timber.d(t)
                }
            })
        }
    }
}
