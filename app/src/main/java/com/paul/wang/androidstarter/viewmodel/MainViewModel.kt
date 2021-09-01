package com.paul.wang.androidstarter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paul.wang.androidstarter.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    val repoListLiveData: LiveData<List<String>> by lazy {
        val repoNameList = MutableLiveData<List<String>>()

        // TODO: Add RxJava to observe and update data back to LiveData.
        //        viewModelScope.launch {
        //            val mainRepoList = MutableLiveData<GithubRepoResponse>()
        //            mainRepository.getRepoData(mainRepoList)
        //            mainRepoList
        //                .map<GithubRepoResponse, List<String>> { githubRepoResponse ->
        //                    val nameList = mutableListOf<String>()
        //                    githubRepoResponse.forEach { githubItem -> nameList.add(githubItem.full_name) }
        //                    Timber.tag("full_name_list").d(nameList.toString())
        //                    nameList
        //                }
        //                .observe(MainViewModel::class, {})
        //        }

        repoNameList
    }
}
