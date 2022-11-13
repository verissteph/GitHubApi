package com.br.studygithubapi.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.studygithubapi.databinding.RepositoryItemBinding
import com.br.studygithubapi.domain.model.Repository
import com.bumptech.glide.Glide

class RepositoryAdapter(
    val repositoryList: MutableList<Repository>
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            RepositoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.binding(this.repositoryList[position])

    }


    override fun getItemCount(): Int {
        return repositoryList.size
    }

    inner class RepositoryViewHolder(val repositoryItemBinding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(repositoryItemBinding.root) {
        fun binding(repository: Repository) {
            repositoryItemBinding.apply {
                username.text = "Author: " + repository.owner.login
                starQnty.text = repository.stargazersCount.toString()
                forkQnty.text = repository.forksCount.toString()
                repositoryName.text = "Repo: " + repository.name

            }

            Glide.with(repositoryItemBinding.photoUser)
                .load(repository.owner.photo)
                .circleCrop()
                .into(repositoryItemBinding.photoUser)

        }
    }
}