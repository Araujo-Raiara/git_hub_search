package com.app.githubsearch.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.githubsearch.databinding.ActivityMainBinding
import com.app.githubsearch.domain.UserRepository
import com.app.githubsearch.ui.adapter.GitHubSearchAdapter
import com.app.githubsearch.ui.viewmodel.GitHubSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val gitHubSearchViewModel : GitHubSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupOberservers()
    }

    private fun setupListeners() {
        binding.apply {
            btnConfirmName.setOnClickListener{
                gitHubSearchViewModel.getAllRepoitories(binding.tietUserName.text.toString())
            }
        }
    }

    private fun setupRecyclerView(list : List<UserRepository>) {
        binding.rvRepositories.adapter = GitHubSearchAdapter(list)
    }

    private fun setupOberservers() {
        gitHubSearchViewModel.listRepositories.observe(this) {
            setupRecyclerView(it)
        }
    }

    private fun setupShareRepository() {
        TODO("to implement")
    }

}