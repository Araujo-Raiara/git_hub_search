package com.app.githubsearch.domain.repository

import com.app.githubsearch.domain.UserRepository

interface GitHubSearchRepository {
    suspend fun getAllRepository(userName: String) : List<UserRepository>
}