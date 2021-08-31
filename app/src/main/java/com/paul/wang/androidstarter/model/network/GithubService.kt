package com.paul.wang.androidstarter.model.network

import com.paul.wang.androidstarter.model.data.GithubRepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    fun getRepo(@Path("user") user: String): Call<GithubRepoResponse>
}
