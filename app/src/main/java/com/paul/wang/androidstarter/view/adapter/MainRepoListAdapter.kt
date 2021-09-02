package com.paul.wang.androidstarter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.paul.wang.androidstarter.databinding.RepoItemBinding
import com.paul.wang.androidstarter.viewmodel.RepoItemData

class MainRepoListAdapter : RecyclerView.Adapter<RepoItemViewHolder>() {
    var repoItemData: RepoItemData? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val binding = DataBindingUtil.inflate<RepoItemBinding>(LayoutInflater.from(parent.context), R.layout.repo_item, parent, false)
        return RepoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.repoName.text = repoItemData?.pairList?.get(position)?.first
        holder.repoDescription.text = repoItemData?.pairList?.get(position)?.second
    }

    override fun getItemCount(): Int = repoItemData?.pairList?.size ?: 0
}

class RepoItemViewHolder(itemBinding: RepoItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    var repoName: TextView = itemBinding.repoName
    var repoDescription: TextView = itemBinding.repoDescription
}
