package com.paul.wang.androidstarter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.paul.wang.androidstarter.databinding.RepoItemBinding
import com.paul.wang.androidstarter.viewmodel.RepoItemData

/**
 * Takes an lambda function as a click listener.
 */
class MainRepoListAdapter(private val onItemClick: (RepoItemData) -> Unit) : RecyclerView.Adapter<RepoItemViewHolder>() {
    var repoItemData: List<RepoItemData>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val binding = DataBindingUtil.inflate<RepoItemBinding>(LayoutInflater.from(parent.context), R.layout.repo_item, parent, false)
        // Pass the click listener to the ViewHolder.
        return RepoItemViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.repoName.text = repoItemData?.get(position)?.title
        holder.repoDescription.text = repoItemData?.get(position)?.description
        // No need to create a new listener and bind the listener to the view here.
        // Only bind data needed to the onclick listener.
        holder.repoItemData = repoItemData?.get(position)
    }

    override fun getItemCount(): Int = repoItemData?.size ?: 0
}

class RepoItemViewHolder(itemBinding: RepoItemBinding, private val onItemClick: (RepoItemData) -> Unit) :
    RecyclerView.ViewHolder(itemBinding.root) {
    // Binding Views
    var item: ConstraintLayout = itemBinding.repoItem
    var repoName: TextView = itemBinding.repoName
    var repoDescription: TextView = itemBinding.repoDescription

    // Binding Data
    var repoItemData: RepoItemData? = null

    init {
        // Set OnClickListener
        item.setOnClickListener {
            repoItemData?.run(onItemClick)
        }
    }
}
