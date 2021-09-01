package com.paul.wang.androidstarter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainRepoListAdapter : RecyclerView.Adapter<RepoItemViewHolder>() {
    var dataList: List<String>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return RepoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.title?.text = dataList?.get(position)
    }

    override fun getItemCount(): Int = dataList?.size ?: 0
}

class RepoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView? = itemView.findViewById(R.id.repoName)
}
