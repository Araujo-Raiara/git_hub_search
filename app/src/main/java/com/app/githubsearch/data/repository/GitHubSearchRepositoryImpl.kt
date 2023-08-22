package com.app.githubsearch.data.repository

import com.app.githubsearch.data.GitHubService
import com.app.githubsearch.domain.UserRepository
import com.app.githubsearch.domain.repository.GitHubSearchRepository


class GitHubSearchRepositoryImpl(
    private val service: GitHubService
) : GitHubSearchRepository {

    override suspend fun getAllRepository(userName: String): List<UserRepository> {
        return service.getAllRepositoriesByUser(userName)
    }

}