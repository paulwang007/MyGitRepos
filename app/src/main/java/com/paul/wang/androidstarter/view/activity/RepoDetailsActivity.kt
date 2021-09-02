package com.paul.wang.androidstarter.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.paul.wang.androidstarter.R
import com.paul.wang.androidstarter.databinding.ActivityRepoDetailsBinding

const val STRING_REPO_URL: String = "STRING_REPO_URL"
const val STRING_REPO_NAME: String = "STRING_REPO_NAME"
const val STRING_REPO_DESCRIPTION: String = "STRING_REPO_DESCRIPTION"

class RepoDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRepoDetailsBinding>(this, R.layout.activity_repo_details)
        binding.lifecycleOwner = this

        binding.repoTitle.text = intent?.getStringExtra(STRING_REPO_NAME)
        binding.repoDescription.text = intent?.getStringExtra(STRING_REPO_DESCRIPTION)
    }
}
