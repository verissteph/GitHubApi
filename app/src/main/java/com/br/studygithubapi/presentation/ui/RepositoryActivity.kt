package com.br.studygithubapi.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.studygithubapi.databinding.ActivityMainBinding
import com.br.studygithubapi.presentation.ui.adapter.RepositoryAdapter
import com.br.studygithubapi.presentation.viewmodel.RepositoryViewModel

class RepositoryActivity : AppCompatActivity() {
    private val adapterRepository = RepositoryAdapter(ArrayList())
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RepositoryViewModel
    private var page = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepositoryViewModel::class.java)
        recyclerInit()
    }

    private fun recyclerInit() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerRepository.apply {
            adapter = adapterRepository
            layoutManager = LinearLayoutManager(this@RepositoryActivity)
            setHasFixedSize(true)
            viewModel.getRepository(page)
            updateList()
        }

    }

    private fun updateList() {
        viewModel.liveData.observe(this) {
            for (repository in it) {
                if (repository !in adapterRepository.repositoryList) {
                    adapterRepository.repositoryList.addAll(it)
                    adapterRepository.notifyDataSetChanged()
                }
                binding.progressbar.visibility = View.GONE
            }
        }
        onScrollListener()
    }

    private fun onScrollListener() {
        binding.recyclerRepository.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if ((linearLayoutManager != null) && (linearLayoutManager.findLastCompletelyVisibleItemPosition() == (adapterRepository.repositoryList.size - 1))) {
                        page += 1
                        viewModel.getRepository(page++)
                        binding.progressbar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}

