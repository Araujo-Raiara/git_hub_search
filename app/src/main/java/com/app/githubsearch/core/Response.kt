package com.app.githubsearch.core

import com.app.githubsearch.domain.UserRepository

sealed class Response {
    data class onSuccess(val listRepositories: List<UserRepository>) : Response()

    data class onError(val error: Throwable) : Response()

    object Loading : Response()
}
