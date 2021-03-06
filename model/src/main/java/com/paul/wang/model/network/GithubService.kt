package com.paul.wang.model.network

import com.paul.wang.model.response.GithubRepoResponse
import com.paul.wang.model.response.RepoDetailsResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    fun getRepo(@Path("user") user: String): Observable<GithubRepoResponse>

    @GET("repos/{user}/{repoName}")
    fun getRepoDetails(@Path("user") user: String, @Path("repoName") repoName: String): Observable<RepoDetailsResponse>
}
