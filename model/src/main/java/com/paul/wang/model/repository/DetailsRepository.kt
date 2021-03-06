package com.paul.wang.model.repository

import com.paul.wang.model.network.GithubService
import retrofit2.Retrofit
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val retrofit: Retrofit) {
    fun getRepoDetails(userName: String, repoName: String) =
        retrofit.create(GithubService::class.java).getRepoDetails(user = userName, repoName = repoName)
}
