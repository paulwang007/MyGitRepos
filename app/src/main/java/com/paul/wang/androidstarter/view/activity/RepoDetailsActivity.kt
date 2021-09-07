package com.paul.wang.androidstarter.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.paul.wang.androidstarter.R
import com.paul.wang.androidstarter.databinding.ActivityRepoDetailsBinding
import com.paul.wang.androidstarter.view.adapter.RepoDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

const val STRING_REPO_URL: String = "STRING_REPO_URL"
const val STRING_REPO_NAME: String = "STRING_REPO_NAME"
const val STRING_REPO_DESCRIPTION: String = "STRING_REPO_DESCRIPTION"

@AndroidEntryPoint
class RepoDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: RepoDetailsViewModel by viewModels()

        val binding = DataBindingUtil.setContentView<ActivityRepoDetailsBinding>(this, R.layout.activity_repo_details)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val repoName = intent?.getStringExtra(STRING_REPO_NAME)
        binding.repoTitle.text = repoName
        binding.repoDescription.text = intent?.getStringExtra(STRING_REPO_DESCRIPTION)

        if (repoName != null) {
            viewModel.getRepoDetails(userName = "paulwang007", repoName = repoName)
        }
    }
}
