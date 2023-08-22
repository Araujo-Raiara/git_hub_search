package com.app.githubsearch.core.di

import com.app.githubsearch.core.Consts
import com.app.githubsearch.data.GitHubService
import com.app.githubsearch.data.repository.GitHubSearchRepositoryImpl
import com.app.githubsearch.domain.repository.GitHubSearchRepository
import com.app.githubsearch.domain.usecase.GetRepositoriesUseCase
import com.app.githubsearch.ui.viewmodel.GitHubSearchViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.math.sin

val appModules = module {
    single { provideRetrofit() }
    single { provideService(get()) }
    single <GitHubSearchRepository> { GitHubSearchRepositoryImpl(get()) }
    single { GetRepositoriesUseCase(get()) }
    viewModel { GitHubSearchViewModel(get()) }

}

fun provideRetrofit(): Retrofit {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    return Retrofit.Builder()
        .baseUrl(Consts.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
}

fun provideService(retrofit: Retrofit) : GitHubService = retrofit.create(GitHubService::class.java)