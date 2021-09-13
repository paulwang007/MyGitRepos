package com.paul.wang.androidstarter.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.paul.wang.androidstarter.R
import com.paul.wang.androidstarter.databinding.ActivityMainBinding
import com.paul.wang.androidstarter.view.adapter.MainRepoListAdapter
import com.paul.wang.viewmodel.MainViewModel
import com.paul.wang.viewmodel.RepoItemData
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

        // To get some previously saved state.
        savedInstanceState?.get("")

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = mainViewModel
        binding.mainRecyclerView.adapter = mainAdapter

        mainViewModel.repoListLiveData.observe(this, { repoNameList ->
            mainAdapter.repoItemData = repoNameList
        })
    }

    /**
     * System will call this after onStart().
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)

        // Some code here.
    }

    /**
     * This might get called any time in between onPause to onDestroy by the system. Or not, if user clicks on back button, or when user doesn't expect UI to be restored.
     */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        // Some code here.

    }
}
