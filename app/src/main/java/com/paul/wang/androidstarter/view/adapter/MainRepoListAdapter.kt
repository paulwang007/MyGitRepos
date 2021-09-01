package com.paul.wang.androidstarter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.paul.wang.androidstarter.databinding.RepoItemBinding

class MainRepoListAdapter : RecyclerView.Adapter<RepoItemViewHolder>() {
    var dataList: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val binding = DataBindingUtil.inflate<RepoItemBinding>(LayoutInflater.from(parent.context), R.layout.repo_item, parent, false)
        return RepoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.title?.text = dataList?.get(position)
    }

    override fun getItemCount(): Int = dataList?.size ?: 0
}

class RepoItemViewHolder(itemBinding: RepoItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    var title: TextView? = itemBinding.repoName
}
