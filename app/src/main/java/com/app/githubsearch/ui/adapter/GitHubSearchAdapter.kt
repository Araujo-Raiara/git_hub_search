package com.app.githubsearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.githubsearch.databinding.RepositoryItemBinding
import com.app.githubsearch.domain.UserRepository

class GitHubSearchAdapter(private val repositoryList: List<UserRepository>, private val onClick : (String) -> Unit) : RecyclerView.Adapter<GitHubSearchAdapter.ViewHolder>() {

    inner class ViewHolder(binding : RepositoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        val btn = binding.btnRepository
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RepositoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount() = repositoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btn.text = repositoryList[position].htmlUrl
        holder.btn.setOnClickListener {
            onClick(repositoryList[position].htmlUrl)
        }
    }
}