package com.app.githubsearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.githubsearch.domain.UserRepository
import com.app.githubsearch.domain.repository.GitHubSearchRepository
import com.app.githubsearch.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.launch

class GitHubSearchViewModel(
    private val getRepositories: GetRepositoriesUseCase
) : ViewModel() {

    private val _listRepositories : MutableLiveData<List<UserRepository>> = MutableLiveData()
    val listRepositories: LiveData<List<UserRepository>> get() = _listRepositories

    fun getAllRepoitories(name: String) {
        viewModelScope.launch {
            val requestResponse = getRepositories.getAllRepositories(name)
            _listRepositories.postValue(requestResponse)
        }

    }
}