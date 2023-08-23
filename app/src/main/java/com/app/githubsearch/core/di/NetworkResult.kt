package com.app.githubsearch.core.di

import com.app.githubsearch.domain.UserRepository

sealed class NetworkResult {
    class Success(val listRepositories: List<UserRepository>) : NetworkResult()
    class Error(val message: Throwable) : NetworkResult()
    object Loading: NetworkResult()
}