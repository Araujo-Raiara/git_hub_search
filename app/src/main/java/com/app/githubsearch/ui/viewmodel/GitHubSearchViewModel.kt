package com.app.githubsearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.githubsearch.core.di.NetworkResult
import com.app.githubsearch.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class GitHubSearchViewModel(
    private val getRepositories: GetRepositoriesUseCase
) : ViewModel() {

    private val _listRepositories: MutableLiveData<NetworkResult> = MutableLiveData()
    val listRepositories: LiveData<NetworkResult> get() = _listRepositories

    fun getAllRepositories(name: String) = viewModelScope.launch {
        kotlin.runCatching {
            val requestResponse = getRepositories.getAllRepositories(name)
            _listRepositories.postValue(NetworkResult.Error(NullPointerException()))
        }.onFailure {
            _listRepositories.postValue(NetworkResult.Error(it))

        }
    }
}