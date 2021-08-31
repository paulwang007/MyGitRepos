package com.paul.wang.mygitrepo.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.wang.mygitrepo.R

class MainScreenAdapter : RecyclerView.Adapter<MainScreenRepoItemViewHolder>() {
    val dataList = listOf("foo", "bar")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenRepoItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_row_item, parent, false)
        return MainScreenRepoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainScreenRepoItemViewHolder, position: Int) {
        holder.repoTitleText.text = dataList[position]
    }

    override fun getItemCount(): Int = dataList.size
}

class MainScreenRepoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // TODO: databinding possible?
    val repoTitleText: TextView = itemView.findViewById(R.id.repoTitle)
}
