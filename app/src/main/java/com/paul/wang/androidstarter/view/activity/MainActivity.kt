package com.paul.wang.androidstarter.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.paul.wang.androidstarter.R
import com.paul.wang.androidstarter.databinding.ActivityMainBinding
import com.paul.wang.androidstarter.view.adapter.MainRepoListAdapter
import com.paul.wang.androidstarter.viewmodel.MainViewModel
import com.paul.wang.androidstarter.viewmodel.RepoItemData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val mainAdapter by lazy {
        MainRepoListAdapter { repoItemData: RepoItemData ->
            val intent = Intent(this, RepoDetailsActivity::class.java)
            intent.putExtra(STRING_REPO_NAME, repoItemData.title)
            intent.putExtra(STRING_REPO_DESCRIPTION, repoItemData.description)
            intent.putExtra(STRING_REPO_URL, repoItemData.htmlUrl.toString())
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = mainViewModel
        binding.mainRecyclerView.adapter = mainAdapter

        mainViewModel.repoListLiveData.observe(this, { repoNameList ->
            mainAdapter.repoItemData = repoNameList
        })
    }
}
