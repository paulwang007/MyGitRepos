package com.paul.wang.androidstarter.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.paul.wang.androidstarter.MainRepoListAdapter
import com.paul.wang.androidstarter.R
import com.paul.wang.androidstarter.databinding.ActivityMainBinding
import com.paul.wang.androidstarter.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewmodel = mainViewModel
        binding.mainRecyclerView.adapter = MainRepoListAdapter()

        mainViewModel.getRepo()
    }
}
