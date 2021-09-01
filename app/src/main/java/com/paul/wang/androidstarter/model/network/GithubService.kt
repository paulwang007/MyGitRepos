package com.paul.wang.androidstarter.model.network

import com.paul.wang.androidstarter.model.data.GithubRepoResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    fun getRepo(@Path("user") user: String): Observable<GithubRepoResponse>
}
