package com.paul.wang.androidstarter.viewmodel

import androidx.lifecycle.ViewModel
import com.paul.wang.androidstarter.model.data.GithubRepoResponse
import com.paul.wang.androidstarter.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun getRepo(): GithubRepoResponse? {
        return mainRepository.getRepoData()
    }
}
