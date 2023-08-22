package com.app.githubsearch.domain

import com.squareup.moshi.Json

data class UserRepository(
val name: String,
@Json(name = "html_url") val htmlUrl: String
)
