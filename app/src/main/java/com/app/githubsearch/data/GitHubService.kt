package com.app.githubsearch.data

import com.app.githubsearch.domain.UserRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun getAllRepositoriesByUser(@Path("user") user: String): List<UserRepository>
}