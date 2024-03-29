package com.app.githubsearch.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.app.githubsearch.core.di.NetworkResult
import com.app.githubsearch.databinding.ActivityMainBinding
import com.app.githubsearch.domain.UserRepository
import com.app.githubsearch.ui.adapter.GitHubSearchAdapter
import com.app.githubsearch.ui.viewmodel.GitHubSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val gitHubSearchViewModel: GitHubSearchViewModel by viewModel()
    private lateinit var adapter: GitHubSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        binding.apply {
            btnConfirmName.setOnClickListener {
                handleLoadingScenery()
                gitHubSearchViewModel.getAllRepositories(binding.tietUserName.text.toString())
            }
        }
    }

    private fun handleSuccessScenery(list: List<UserRepository>) {
        adapter = GitHubSearchAdapter(list, ::getUrlFromIntent)
        binding.rvRepositories.adapter = adapter

    }

    private fun handleErrorResponse(error: Throwable) {
        binding.apply {
            avLoading.isVisible = false
            errorScenery.isVisible = true
            rvRepositories.isVisible = false
            btnConfirmName.isVisible = false
            tilUserName.isVisible = false
            btnTryAgain.isVisible = true
        }
        gitHubSearchViewModel.getAllRepositories(binding.tietUserName.text.toString())
        print(error.message)
        Toast.makeText(this, "Algo de errado aconteceu, tente novamente", Toast.LENGTH_SHORT).show()
    }

    private fun setupObservers() {
        gitHubSearchViewModel.listRepositories.observe(this) {
            when (it) {
                is NetworkResult.Error -> handleErrorResponse(it.message)
                NetworkResult.Loading -> handleLoadingScenery()
                is NetworkResult.Success -> {
                    binding.avLoading.isVisible = false
                    handleSuccessScenery(it.listRepositories)
                }
            }
        }
    }

    private fun handleLoadingScenery() {
        binding.avLoading.isVisible = true
    }

    private fun setupShareRepository() {
        // TODO("to implement")
    }

    private fun getUrlFromIntent(urlRepository: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(urlRepository)
        startActivity(intent)
    }
}