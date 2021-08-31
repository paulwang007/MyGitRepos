package com.paul.wang.androidstarter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainRepoListAdapter : RecyclerView.Adapter<RepoItemViewHolder>() {
    val fakeList = listOf("foo", "bar", "foo", "bar", "foo", "bar", "foo", "bar", "foo", "bar", "foo", "bar", "foo", "bar")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return RepoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.title?.text = fakeList[position]
    }

    override fun getItemCount(): Int = fakeList.size
}

class RepoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView? = itemView.findViewById<TextView>(R.id.repoName)
}
