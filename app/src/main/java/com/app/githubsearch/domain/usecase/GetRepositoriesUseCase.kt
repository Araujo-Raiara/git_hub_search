package com.app.githubsearch.domain.usecase

import com.app.githubsearch.domain.UserRepository
import com.app.githubsearch.domain.repository.GitHubSearchRepository

class GetRepositoriesUseCase(
    private val repository: GitHubSearchRepository
) {
   suspend fun getAllRepositories(name: String) : List<UserRepository>{
        return repository.getAllRepository(name)
    }
}